package com.example.wd_mvvm.repository

import com.example.wd_mvvm.models.NewsApiResponse
import com.example.wd_mvvm.repository.remote.RemoteDataSourceImpl
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Callback

class Repository : KoinComponent {

    private val remoteDataSource by inject<RemoteDataSourceImpl>()

    fun getNews(sources: String, callback: Callback<NewsApiResponse>) {
        remoteDataSource.getNews(sources, callback)
    }
}
