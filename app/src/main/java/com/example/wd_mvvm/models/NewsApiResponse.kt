package com.example.wd_mvvm.models

import com.example.wd_mvvm.models.article.Article
import com.google.gson.annotations.SerializedName

data class NewsApiResponse (
    @SerializedName(value = "status")
    var status: String,
    @SerializedName(value = "totalResults")
    var totalResults: Int,
    @SerializedName(value = "articles")
    var articles: MutableList<Article>
)
