package com.example.newsapp.data.remote;

import com.example.newsapp.domain.models.Article;

import java.util.List;

public class NewsResponseDto {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
