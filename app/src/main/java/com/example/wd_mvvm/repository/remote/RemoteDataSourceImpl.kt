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

    fun getNews(sources: String): MutableLiveData<NewsApiResponse> {
        val news = MutableLiveData<NewsApiResponse>()
        api.getNews(sources, Constants.apiKey).enqueue(object : Callback<NewsApiResponse>{
            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                news.value=null
            }

            override fun onResponse(call: Call<NewsApiResponse>, response: Response<NewsApiResponse>) {
                if (response.isSuccessful){
                    news.value = response.body()
                }
            }
        })
        return news
    }
}
