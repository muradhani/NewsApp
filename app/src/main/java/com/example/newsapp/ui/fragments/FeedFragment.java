package com.example.newsapp.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newsapp.R;
import com.example.newsapp.data.remote.NewsResponseDto;
import com.example.newsapp.databinding.FragmentFeedBinding;
import com.example.newsapp.domain.models.Article;
import com.example.newsapp.ui.adapter.NewsItemClickListener;
import com.example.newsapp.ui.adapter.NewsRvAdapter;
import com.example.newsapp.ui.adapter.TopStoriesRvAdapter;
import com.example.newsapp.ui.viewModels.FeedFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment implements NewsItemClickListener {

    private FeedFragmentViewModel viewModel;
    private FragmentFeedBinding binding; // Generated from data binding
    private List<Article> topStoriesList = new ArrayList<>();

    private TopStoriesRvAdapter topStoriesAdapter;
    private NewsRvAdapter newsAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FeedFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchTopHeadlines();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout using data binding

        binding = FragmentFeedBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        // Set the ViewModel to the binding
       // binding.setViewModel(viewModel);
        // Specify the fragment view
        viewModel.fetchTopHeadlines();
        // Initialize adapters with empty lists
        topStoriesAdapter = new TopStoriesRvAdapter(topStoriesList);


        // Set adapters to RecyclerViews
        binding.topStoriesRv.setAdapter(topStoriesAdapter);
        binding.newsRv.setAdapter(newsAdapter);

        // Set layout managers for RecyclerViews
//        binding.topStoriesRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
//        binding.newsRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        // Here you can fetch data and update the lists used by adapters if needed
        // For now, let's assume you have already populated the lists with data
        // You can call updateData() method on adapters to update the data

        // Example:
        // topStoriesList.addAll(someData);
        // topStoriesAdapter.updateData(topStoriesList);
        // Observe LiveData from ViewModel
        viewModel.getNewsResponse().observe(getViewLifecycleOwner(), new Observer<NewsResponseDto>() {
            @Override
            public void onChanged(NewsResponseDto newsResponse) {
                // Update RecyclerView adapters with new data
                topStoriesList = newsResponse.getArticles();
                topStoriesAdapter.setData(topStoriesList);
                newsAdapter.setData(topStoriesList);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.fetchTopHeadlines();
        // Initialize adapters with empty lists
        topStoriesAdapter = new TopStoriesRvAdapter(topStoriesList);
        newsAdapter = new NewsRvAdapter(topStoriesList,this);

        // Set adapters to RecyclerViews
        binding.topStoriesRv.setAdapter(topStoriesAdapter);
        binding.newsRv.setAdapter(newsAdapter);

        // Set layout managers for RecyclerViews
//        binding.topStoriesRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
//        binding.newsRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        // Here you can fetch data and update the lists used by adapters if needed
        // For now, let's assume you have already populated the lists with data
        // You can call updateData() method on adapters to update the data

        // Example:
        // topStoriesList.addAll(someData);
        // topStoriesAdapter.updateData(topStoriesList);
        // Observe LiveData from ViewModel
        viewModel.getNewsResponse().observe(getViewLifecycleOwner(), new Observer<NewsResponseDto>() {
            @Override
            public void onChanged(NewsResponseDto newsResponse) {
                // Update RecyclerView adapters with new data
                topStoriesList = newsResponse.getArticles();
                topStoriesAdapter.setData(topStoriesList);
                newsAdapter.setData(topStoriesList);
            }
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                // Handle error message if needed
            }
        });
    }

    @Override
    public void onProductClicked(Article item) {
        Bundle bundle = new Bundle();
        bundle.putString("title", item.getTitle());
        bundle.putString("imageUrl", item.getUrlToImage());
        bundle.putString("body", item.getDescription());

        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_feedFragment_to_newsDetailedFragment, bundle);
    }
}
