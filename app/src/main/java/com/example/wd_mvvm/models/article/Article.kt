package com.example.wd_mvvm.models.article

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL

@Entity(tableName = "article")
data class Article (
    var source: Source,
    var author: String,
    @PrimaryKey
    var title: String,
    var description: String,
    var url: URL,
    var urlToImage: URL,
    var publishedAt: String,
    var content: String
)