package com.example.wd_mvvm.repository.local

import androidx.room.TypeConverter
import com.example.wd_mvvm.models.article.Source
import java.io.Serializable
import java.net.URL

class Converters {
    @TypeConverter
    fun fromSource(source: Source?): String?{
        return source?.name + "/./" + source?.id
    }

    @TypeConverter
    fun stringToSource(string: String?) : Source? {
        val split = string?.split("/./")
        return Source(split?.get(1).toString(), split?.get(0).toString())
    }

    @TypeConverter
    fun fromURL(url: URL?) : String? {
        return url.toString()
    }

    @TypeConverter
    fun stringToUrl(string: String?) : URL? {
        return URL(string)
    }
}