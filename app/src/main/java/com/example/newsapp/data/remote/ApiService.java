package com.example.newsapp.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface ApiService {
    @GET("top-headlines")
    Call<NewsResponseDto> getTopHeadlines(
            @Query("sources") String source
    );
}
