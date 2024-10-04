package com.kotlin.mvvm.repository.db.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.mvvm.repository.model.news.News
import kotlinx.coroutines.flow.Flow


/**
 * Abstracts access to the news database
 */
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<News>): List<Long>

    @Query("SELECT * FROM news_table")
    fun getNewsArticles(): Flow<List<News>>

    @Query("DELETE FROM news_table")
    suspend fun deleteAllArticles()
}