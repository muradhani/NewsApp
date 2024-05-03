package com.example.newsapp.ui.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.data.remote.NewsResponseDto;
import com.example.newsapp.databinding.FragmentFeedBinding;
import com.example.newsapp.databinding.FragmentNewsDetailedBinding;
import com.example.newsapp.domain.models.Article;
import com.example.newsapp.ui.adapter.RelatedStoriesRvAdapter;
import com.example.newsapp.ui.viewModels.NewsDetailedFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailedFragment extends Fragment {
    private NewsDetailedFragmentViewModel viewModel;
    private FragmentNewsDetailedBinding binding; // Generated from data binding
    private List<Article> newsList = new ArrayList<>();
    private RelatedStoriesRvAdapter adapter;
    String title ;
    String imageUrl ;
    String body;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NewsDetailedFragmentViewModel.class);
        title = getArguments().getString("title");
        imageUrl = getArguments().getString("imageUrl");
        body = getArguments().getString("body");
        System.out.println(body);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsDetailedBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        binding.title.setText(title);
        binding.body.setText(body);
        Glide.with(binding.getRoot()).load(imageUrl).into(binding.imageView);
        viewModel.fetchTopHeadlines();
        adapter = new RelatedStoriesRvAdapter(newsList);
        binding.relatedStoriesRv.setAdapter(adapter);
        viewModel.getNewsResponse().observe(getViewLifecycleOwner(), new Observer<NewsResponseDto>() {
            @Override
            public void onChanged(NewsResponseDto newsResponse) {
                // Update RecyclerView adapters with new data
                newsList  = newsResponse.getArticles();
                adapter.setData(newsList);
            }
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                // Handle error message if needed
            }
        });

        return binding.getRoot();
    }
}