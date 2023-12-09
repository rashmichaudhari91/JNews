package com.rashmi.jnews.helper;

import com.rashmi.jnews.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {

    @GET("everything?q=android")
    Call<APIResponse> getNews(
            @Query("page") Integer page,
            @Query("apiKey") String apiKey
    );

}
