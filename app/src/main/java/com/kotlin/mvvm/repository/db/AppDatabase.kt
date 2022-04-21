package com.kotlin.mvvm.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.mvvm.repository.db.countries.CountriesDao
import com.kotlin.mvvm.repository.db.news.NewsDao
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.repository.model.news.News

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * App Database
 * Define all entities and access doa's here/ Each entity is a table.
 */
@Database(entities = [News::class, Country::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsArticlesDao(): NewsDao

    abstract fun countriesDao(): CountriesDao
}