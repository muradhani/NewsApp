package com.example.newsapp.ui.adapter;

import com.example.newsapp.R;
import com.example.newsapp.databinding.NewsRvItemBinding;
import com.example.newsapp.databinding.RelatedStoriesRvItemBinding;
import com.example.newsapp.domain.models.Article;
import com.example.newsapp.ui.base.BaseRecyclerViewAdapter;

import java.util.List;

public class RelatedStoriesRvAdapter extends BaseRecyclerViewAdapter<RelatedStoriesRvItemBinding, Article> {

    public RelatedStoriesRvAdapter(List<Article> initialData) {
        super(initialData);
    }
    @Override
    public void bind(RelatedStoriesRvItemBinding binding, Article item) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.top_stories_rv_item;
    }
}