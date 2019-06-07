package com.example.wd_mvvm.ui.news

import android.app.Application
import androidx.lifecycle.*
import com.example.wd_mvvm.models.NewsApiResponse
import com.example.wd_mvvm.models.article.Article
import com.example.wd_mvvm.repository.ArticleRepository
import com.example.wd_mvvm.repository.Repository
import com.example.wd_mvvm.repository.local.ArticleRoomDatabase
import com.example.wd_mvvm.ui.main.MainViewModel
import io.reactivex.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    sealed class Command {
        class BackScreen(val name: String) : Command()
    }

    private var _command = MutableLiveData<MainViewModel.Command>()
    val command: LiveData<MainViewModel.Command> get() = _command
    private val repository by inject<Repository>()
    private val articleRepository: ArticleRepository
    private var newsModel : MutableLiveData<NewsApiResponse>? = null

    init {
        val articleDao = ArticleRoomDatabase.getDatabase(application, viewModelScope).articleDao()
        articleRepository = ArticleRepository(articleDao)
        newsModel = repository.getNews("bbc-news")
    }

    fun insertAll(articles: List<Article>) = viewModelScope.launch(Dispatchers.IO) {
        for (article: Article in articles) {
            if (article.content != null)
                articleRepository.insert(article)
        }
    }

    fun getNews(): LiveData<NewsApiResponse>? {
        return newsModel
    }

    fun getSoredArticles(): LiveData<List<Article>> {
        return articleRepository.allArticles
    }

}