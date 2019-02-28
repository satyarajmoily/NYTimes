package com.satyaraj.app.nytimes.application;

import android.app.Activity;
import android.app.Application;

import com.satyaraj.app.nytimes.ApiCall;
import com.satyaraj.app.nytimes.application.di.ApplicationModule;

import com.satyaraj.app.nytimes.application.di.AppComponent;
import com.satyaraj.app.nytimes.application.di.DaggerAppComponent;
import com.satyaraj.app.nytimes.database.AppDatabase;

public class StoriesApplication extends Application {

    private static AppDatabase appDatabase;
    private ApiCall apiCall;
    private  AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponents();
    }

    private void initializeComponents() {
        //    private static SharedPrefsHelper sharedPrefsHelper;

        appComponent = DaggerAppComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
//        sharedPrefsHelper = appComponent.getSharedPrefsHelper();
        appDatabase = appComponent.getAppDatabase();
        apiCall = appComponent.getApiCall();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

//    public static SharedPrefsHelper getSharedPrefsHelper() {
//        return sharedPrefsHelper;
//    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static StoriesApplication get(Activity activity) {
        return (StoriesApplication) activity.getApplication();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ApiCall getApiCall() {
        return apiCall;
    }
}
