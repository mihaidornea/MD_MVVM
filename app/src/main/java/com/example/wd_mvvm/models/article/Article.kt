package com.example.wd_mvvm.models.article

import java.net.URL

data class Article (
    var source: Source,
    var author: String,
    var title: String,
    var description: String,
    var url: URL,
    var urlToImage: URL,
    var publishedAt: String,
    var content: String
)