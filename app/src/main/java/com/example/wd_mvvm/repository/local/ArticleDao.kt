package com.example.wd_mvvm.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wd_mvvm.models.article.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)
    @Update
    suspend fun update(article: Article)
    @Query("DELETE FROM article")
    fun deleteAll()
    @Query("SELECT * FROM article ORDER BY title ASC")
    fun getAllArticles() : LiveData<List<Article>>


}