package com.rashmi.jnews.ui.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.rashmi.jnews.R;
import com.rashmi.jnews.databinding.FragmentNewsDescriptionBinding;
import com.rashmi.jnews.model.ArticlesItem;
import com.rashmi.jnews.ui.viewmodel.SharedViewModel;

public class NewsDescriptionFragment extends Fragment {

    private FragmentNewsDescriptionBinding _binding;

    private FragmentNewsDescriptionBinding getBinding() {
        return _binding;
    }

    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _binding = FragmentNewsDescriptionBinding.inflate(inflater, container, false);
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getSelectedNewsItem().observe(getViewLifecycleOwner(), new Observer<ArticlesItem>() {
            @Override
            public void onChanged(ArticlesItem selectedNewsItem) {
                if (selectedNewsItem != null) {
                    Glide.with(requireActivity())
                            .load(selectedNewsItem.getUrlToImage())
                            .placeholder(R.drawable.ic_menu_camera)
                            .into(getBinding().newsDetailImage);

                    getBinding().newsDetailTitle.setText(selectedNewsItem.getTitle());
                    getBinding().newsDetailContent.setText(selectedNewsItem.getContent());
                    getBinding().newsDetailUrl.setText(selectedNewsItem.getUrl());
                }
            }
        });
    }

    public void onUrlClicked(View view) {
        String url = ((TextView) view).getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
