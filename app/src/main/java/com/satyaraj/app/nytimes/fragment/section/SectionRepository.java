package com.satyaraj.app.nytimes.fragment.section;


import android.util.Log;

import com.satyaraj.app.nytimes.ApiCall;
import com.satyaraj.app.nytimes.base.BaseRepository;
import com.satyaraj.app.nytimes.database.AppDatabase;
import com.satyaraj.app.nytimes.pojo.ListOfStories;
import com.satyaraj.app.nytimes.pojo.Stories;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;
import static com.satyaraj.app.nytimes.utils.Constants.API_KEY;

public class SectionRepository extends BaseRepository<SectionPresenter> {

    private CompositeDisposable mCompositeDisposable;
    private ApiCall mApiCall;
    private AppDatabase mAppDatabase;

    @Inject
    public SectionRepository(ApiCall apiCall, CompositeDisposable compositeDisposable, AppDatabase appDatabase) {
        this.mCompositeDisposable = compositeDisposable;
        this.mApiCall = apiCall;
        this.mAppDatabase = appDatabase;
    }

    public void fetchStories(String section){
        mCompositeDisposable.add(mApiCall.requestForStories(section, API_KEY)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ListOfStories>() {

                            @Override
                            public void onSuccess(ListOfStories stories) {
                                if (stories != null) {
                                    getActions().fetchedStories(stories.getStories());
                                    cacheStories(stories.getStories());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG,  e.getMessage());
                            }
                        })
        );
    }

    public void cacheStories(List<Stories> storiesList){
        mCompositeDisposable.add(Observable.fromCallable(() -> {
            mAppDatabase.storiesDao().delete();
            mAppDatabase.storiesDao().insertAll(storiesList);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void getStoriesFromDatabase(String section){
        mCompositeDisposable.add(Observable.fromCallable(() -> mAppDatabase.storiesDao().getAll())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storiesList -> {
                    if (storiesList.isEmpty()){
                        fetchStories(section);
                    }else {
                        getActions().fetchedStories(storiesList);
                        fetchStories(section);
                    }
                }));
    }
}
