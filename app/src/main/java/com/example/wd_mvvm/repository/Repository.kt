package com.example.wd_mvvm.repository

import androidx.lifecycle.MutableLiveData
import com.example.wd_mvvm.models.NewsApiResponse
import com.example.wd_mvvm.repository.remote.RemoteDataSourceImpl
import org.koin.core.KoinComponent
import org.koin.core.inject

class Repository : KoinComponent{

    private val remoteDataSource by inject<RemoteDataSourceImpl>()

    fun getNews(sources: String) : MutableLiveData<NewsApiResponse> {
        return remoteDataSource.getNews(sources)
    }
}
