package com.rashmi.jnews.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rashmi.jnews.helper.RetrofitClient;
import com.rashmi.jnews.model.APIResponse;
import com.rashmi.jnews.repo.NewsRepo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class HomeViewModel extends ViewModel {

    private final NewsRepo repo;
    private final MutableLiveData<APIResponse> _news = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return _isLoading;
    }

    public LiveData<APIResponse> getNews() {
        return _news;
    }

    public HomeViewModel() {
        repo = new NewsRepo(RetrofitClient.getApiInstance());
        getNewsAll();
    }

    public void setNews(APIResponse newsList) {
        _news.setValue(newsList);
    }

    public void getNewsAll() {
        Executor executor = Executors.newSingleThreadExecutor();
        _isLoading.postValue(true);
        executor.execute(() -> {
            APIResponse apiResponse = repo.getNews();
            _news.postValue(apiResponse);
            _isLoading.postValue(false);
        });
    }
}


