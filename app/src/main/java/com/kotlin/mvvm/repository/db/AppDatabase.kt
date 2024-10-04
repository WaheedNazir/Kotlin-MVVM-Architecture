package com.kotlin.mvvm.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.mvvm.repository.db.countries.CountriesDao
import com.kotlin.mvvm.repository.db.news.NewsDao
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.repository.model.news.News


/**
 * App Database
 * Define all entities and access DAO's here/ Each entity is a table.
 */
@Database(entities = [News::class, Country::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsArticlesDao(): NewsDao
    abstract fun countriesDao(): CountriesDao
}