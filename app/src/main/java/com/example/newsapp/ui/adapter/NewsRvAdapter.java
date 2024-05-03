package com.example.newsapp.ui.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.databinding.NewsRvItemBinding;
import com.example.newsapp.databinding.TopStoriesRvItemBinding;
import com.example.newsapp.domain.models.Article;
import com.example.newsapp.ui.base.BaseRecyclerViewAdapter;

import java.util.List;

public class NewsRvAdapter extends BaseRecyclerViewAdapter<NewsRvItemBinding, Article> {
    private NewsItemClickListener listener;

    public NewsRvAdapter(List<Article> initialData, NewsItemClickListener listener) {
        super(initialData);
        this.listener = listener;
    }
    @Override
    public void bind(NewsRvItemBinding binding, Article item) {
        Glide.with(binding.getRoot()).load(item.getUrlToImage()).into(binding.imgProduct);
        binding.body.setText( item.getDescription());
        binding.title.setText(item.getTitle());
        binding.getRoot().setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                listener.onProductClicked(item);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.news_rv_item;
    }
}
