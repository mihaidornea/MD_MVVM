package com.example.wd_mvvm.models

import com.example.wd_mvvm.models.article.Article

data class NewsApiResponse (
    var status: String,
    var totalResults: Int,
    var articles: MutableList<Article>
)
