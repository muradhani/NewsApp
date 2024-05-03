package com.example.newsapp.ui.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentFeedBinding;
import com.example.newsapp.ui.viewModels.NewsDetailedFragmentViewModel;

public class NewsDetailedFragment extends Fragment {
    private NewsDetailedFragmentViewModel viewModel;
    private FragmentFeedBinding binding; // Generated from data binding

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NewsDetailedFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        //binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}