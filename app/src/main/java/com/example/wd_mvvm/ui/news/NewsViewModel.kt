package com.example.wd_mvvm.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wd_mvvm.models.NewsApiResponse
import com.example.wd_mvvm.models.article.Article
import com.example.wd_mvvm.repository.Repository
import com.example.wd_mvvm.ui.main.MainViewModel
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    sealed class Command {
        class BackScreen(val name: String) : Command()
        class ErrorToast(val error: String) : Command()
    }

    private var _command = MutableLiveData<Command>()
    val command: LiveData<Command> get() = _command
    private val repository by inject<Repository>()
    private var newsModel : MutableLiveData<NewsApiResponse>? = MutableLiveData()
    private var realm: Realm = Realm.getDefaultInstance()

    private fun getNewsRemote() {
        repository.getNews("bbc-news", object : Callback<NewsApiResponse> {
            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                _command.value = Command.ErrorToast(t.message.toString())
            }

            override fun onResponse(call: Call<NewsApiResponse>, response: Response<NewsApiResponse>) {
                response.body()?.let {
                    newsModel?.value = it
                }
            }
        })
    }

    fun insertAll(articles: MutableList<Article>){
        for (article : Article in articles)
            if (article.content == null)
                article.content = ""
        realm.executeTransaction { realm -> realm.copyToRealm(articles) }
    }


    fun getNews(isInternetAvailable: Boolean): LiveData<NewsApiResponse>? {
        if (!isInternetAvailable){
            getStoredArticles()
        } else {
            getNewsRemote()
        }
        return newsModel
    }

    private fun getStoredArticles() {
        realm.executeTransaction { realm ->
            val replacement = NewsApiResponse()
            val realmArticles = realm.where(Article::class.java).findAll()
            for (article in realmArticles){
                replacement.articles?.add(article)
                _command.value = Command.ErrorToast(article.content)
            }
            newsModel?.postValue(replacement)
        }
    }

}