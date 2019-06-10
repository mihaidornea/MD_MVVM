package com.example.wd_mvvm.models.article

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Article (
    var source: Source? = Source("", ""),
    var author: String = "",
    var title: String  = "",
    var description: String = "",
    var url: String = "",
    var urlToImage: String = "",
    var publishedAt: String = "",
    var content: String = ""
) : RealmObject()