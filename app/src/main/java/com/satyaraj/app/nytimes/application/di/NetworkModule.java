package com.satyaraj.app.nytimes.application.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.satyaraj.app.nytimes.ApiCall;
import com.satyaraj.app.nytimes.BuildConfig;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @AppScope
    public ApiCall getApiCall(Retrofit apiCallRetrofit) {
        return apiCallRetrofit.create(ApiCall.class);
    }

    @Provides
    @AppScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @AppScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BuildConfig.NYTIMES_BASE_URL)
                .build();
    }

    @Provides
    @AppScope
    public OkHttpClient okHttpClient() {

        return new OkHttpClient.Builder()
                .build();
    }

}
