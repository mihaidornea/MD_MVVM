package com.example.wd_mvvm.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.wd_mvvm.models.article.Article
import com.example.wd_mvvm.repository.local.ArticleDao

class ArticleRepository(private val articleDao : ArticleDao) {

    val allArticles : LiveData<List<Article>> = articleDao.getAllArticles()

    @WorkerThread
    suspend fun insert(article: Article){
        articleDao.insert(article)
    }
}