package com.rashmi.jnews.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rashmi.jnews.model.ArticlesItem;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<ArticlesItem> selectedNewsItem = new MutableLiveData<>();

    public MutableLiveData<ArticlesItem> getSelectedNewsItem() {
        return selectedNewsItem;
    }
}