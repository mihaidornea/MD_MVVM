package com.example.wd_mvvm.repository.remote

import com.example.wd_mvvm.models.NewsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("top-headlines")
    fun getNews(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ) : Call<NewsApiResponse>

}