package com.satyaraj.app.nytimes.application.di;

import com.satyaraj.app.nytimes.ApiCall;
import com.satyaraj.app.nytimes.database.AppDatabase;

import dagger.Component;

@AppScope
//@Component(modules = {SharedPrefsModule.class})
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface AppComponent {
    AppDatabase getAppDatabase();

    ApiCall getApiCall();

//    SharedPrefsHelper getSharedPrefsHelper();
}
