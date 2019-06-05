package com.example.wd_mvvm.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wd_mvvm.models.NewsApiResponse
import com.example.wd_mvvm.repository.Repository
import com.example.wd_mvvm.ui.main.MainViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsViewModel : ViewModel(), KoinComponent {

    sealed class Command {
        class BackScreen(val name: String) : Command()
    }

    private var _command = MutableLiveData<MainViewModel.Command>()
    val command: LiveData<MainViewModel.Command> get() = _command
    private val repository by inject<Repository>()

    private var newsModel : MutableLiveData<NewsApiResponse>? = null

    fun init() {
        newsModel = repository.getNews("bbc-news")
    }

    fun getNews(): LiveData<NewsApiResponse>? {
        return newsModel
    }

}