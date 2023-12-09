package com.rashmi.jnews.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final NewsAPI apiInstance = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI.class);

    public static NewsAPI getApiInstance() {
        return apiInstance;
    }
}
