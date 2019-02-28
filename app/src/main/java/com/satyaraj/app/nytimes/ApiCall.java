package com.satyaraj.app.nytimes;

import com.satyaraj.app.nytimes.pojo.ListOfStories;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("{section}.json")
    Single<ListOfStories> requestForStories(@Path("section") String section,
                                            @Query("api-key") String apiKey);

}
