package com.satyaraj.app.nytimes.application.di;

import android.content.Context;

import com.satyaraj.app.nytimes.application.StoriesApplication;
import com.satyaraj.app.nytimes.database.AppDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Context context;
    private final StoriesApplication application;

    public ApplicationModule(StoriesApplication context) {
        this.application = context;
        this.context = context.getApplicationContext();
    }

    @Provides
    public StoriesApplication getApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    public Context context() {
        return context;
    }

    @Provides
    public AppDatabase getAppDatabase(){
        return AppDatabase.getAppDatabase(context);
    }
}