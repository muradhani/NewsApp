package com.example.newsapp.ui.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.data.remote.ApiService;
import com.example.newsapp.data.remote.NewsResponseDto;
import com.example.newsapp.data.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailedFragmentViewModel extends ViewModel {
    private MutableLiveData<NewsResponseDto> newsResponse = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<NewsResponseDto> getNewsResponse() {
        return newsResponse;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchTopHeadlines() {
        ApiService newsApiService = RetrofitClient.getNewsApiService();
        Call<NewsResponseDto> call = newsApiService.getTopHeadlines("bbc-news");
        call.enqueue(new Callback<NewsResponseDto>() {
            @Override
            public void onResponse(Call<NewsResponseDto> call, Response<NewsResponseDto> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    newsResponse.postValue(response.body());
                    // Process the news response
                } else {
                    // Handle unsuccessful response
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<NewsResponseDto> call, Throwable t) {
                // Handle failure
                String errorMessage = t.getMessage();

            }
        });
    }
}
