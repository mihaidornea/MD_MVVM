package com.example.wd_mvvm.repository.remote

import androidx.lifecycle.MutableLiveData
import com.example.wd_mvvm.Constants
import com.example.wd_mvvm.models.NewsApiResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : KoinComponent {

    private val api by inject<RemoteDataSource>()

    fun getNews(sources: String, callback: Callback<NewsApiResponse>) {
        api.getNews(sources, Constants.apiKey).enqueue(callback)
    }
}
