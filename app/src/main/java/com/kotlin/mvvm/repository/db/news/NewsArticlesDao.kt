package com.kotlin.mvvm.repository.db.news

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.mvvm.repository.model.news.NewsArticles

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * Abstracts access to the news database
 */
@Dao
interface NewsArticlesDao {

    /**
     * Insert articles into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<NewsArticles>): List<Long>

    /**
     * Get all the articles from database
     */
    @Query("SELECT * FROM news_table")
    fun getNewsArticles(): LiveData<List<NewsArticles>>

    @Query("DELETE FROM news_table")
    abstract fun deleteAllArticles()
}