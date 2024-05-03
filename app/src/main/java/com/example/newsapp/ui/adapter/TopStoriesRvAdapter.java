package com.example.newsapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.databinding.TopStoriesRvItemBinding;
import com.example.newsapp.domain.models.Article;
import com.example.newsapp.ui.base.BaseRecyclerViewAdapter;
import com.example.newsapp.ui.base.BaseViewHolder;

import java.util.List;


public class TopStoriesRvAdapter extends BaseRecyclerViewAdapter<TopStoriesRvItemBinding, Article> {

    public TopStoriesRvAdapter(List<Article> initialData) {
        super(initialData);
    }
    @Override
    public void bind(TopStoriesRvItemBinding binding, Article item) {
        Glide.with(binding.getRoot()).load(item.getUrlToImage()).into(binding.imgNews);
    }

    @Override
    public int getLayoutId() {
        return R.layout.top_stories_rv_item;
    }
}