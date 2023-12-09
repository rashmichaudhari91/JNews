package com.rashmi.jnews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.rashmi.jnews.R;
import com.rashmi.jnews.databinding.FragmentHomeBinding;
import com.rashmi.jnews.model.ArticlesItem;
import com.rashmi.jnews.ui.news.NewsAdapter;
import com.rashmi.jnews.ui.viewmodel.HomeViewModel;
import com.rashmi.jnews.ui.viewmodel.SharedViewModel;

import java.util.List;

public class NewsFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private SharedViewModel sharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        homeViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));

        homeViewModel.getNews().observe(getViewLifecycleOwner(), newsList -> {
            if (newsList != null && newsList.getArticles() != null && !newsList.getArticles().isEmpty()) {
                binding.tblHomeNews.removeAllViews();
                NewsAdapter newsAdapter = (NewsAdapter) binding.tblHomeNews.getTag();
                if (newsAdapter == null) {
                    newsAdapter = new NewsAdapter(newsList.getArticles());
                    binding.tblHomeNews.setTag(newsAdapter);
                }
                for (int i = 0; i < newsAdapter.getCount(); i++) {
                    TableRow tableRow = (TableRow) newsAdapter.getView(i, null, binding.tblHomeNews);
                    binding.tblHomeNews.addView(tableRow);
                    int finalI = i;
                    tableRow.setOnClickListener(view1 -> {
                        ArticlesItem selectedNewsItem = newsList.getArticles().get(finalI);
                        sharedViewModel.getSelectedNewsItem().setValue(selectedNewsItem);
                        navigateToNewsDescriptionFragment();
                    });
                }
            }
        });
    }

    private void navigateToNewsDescriptionFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_nav_home_to_newsDescriptionFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}