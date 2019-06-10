package com.example.wd_mvvm.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wd_mvvm.models.NewsApiResponse
import com.example.wd_mvvm.models.article.Article
import com.example.wd_mvvm.repository.Repository
import com.example.wd_mvvm.ui.main.MainViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel(), KoinComponent {

    sealed class Command {
        class BackScreen(val name: String) : Command()
    }

    private var _command = MutableLiveData<MainViewModel.Command>()
    val command: LiveData<MainViewModel.Command> get() = _command
    private val repository by inject<Repository>()
    var newsModel: MutableLiveData<NewsApiResponse> = MutableLiveData()

    fun getNews() {
        repository.getNews("bbc-news", object : Callback<NewsApiResponse> {
            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<NewsApiResponse>, response: Response<NewsApiResponse>) {
                response.body()?.let {
                    newsModel.value = it
                }
            }

        })

    }
}