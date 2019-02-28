package com.satyaraj.app.nytimes.fragment.section.di;

import com.satyaraj.app.nytimes.ApiCall;
import com.satyaraj.app.nytimes.application.StoriesApplication;
import com.satyaraj.app.nytimes.database.AppDatabase;
import com.satyaraj.app.nytimes.fragment.section.SectionFragment;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class SectionModule {

    private SectionFragment mSectionFragment;

    public SectionModule(SectionFragment mSectionFragment) {
        this.mSectionFragment = mSectionFragment;
    }

    @Provides
    SectionFragment getSectionFragment(){
        return mSectionFragment;
    }

    @Provides
    ApiCall providesApiCall() {
        return StoriesApplication.get(mSectionFragment.getParentActivity()).getAppComponent().getApiCall();
    }

    @Provides
    CompositeDisposable getCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    AppDatabase getAppDatabase(){
        return StoriesApplication.getAppDatabase();
    }

}
