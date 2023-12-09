package com.rashmi.jnews.repo;

import com.rashmi.jnews.constant.IConstant;
import com.rashmi.jnews.helper.NewsAPI;
import com.rashmi.jnews.model.APIResponse;

import java.io.IOException;

import retrofit2.Response;

public class NewsRepo {

    private final NewsAPI api;

    public NewsRepo(NewsAPI api) {
        this.api = api;
    }

    public APIResponse getNews() {
        try {
            Response<APIResponse> response = api.getNews(1, IConstant.apiKey).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }
}
