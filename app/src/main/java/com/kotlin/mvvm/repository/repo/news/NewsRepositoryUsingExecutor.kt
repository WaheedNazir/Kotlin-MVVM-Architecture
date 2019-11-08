package com.kotlin.mvvm.repository.repo.news

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.kotlin.mvvm.app.AppExecutors
import com.kotlin.mvvm.repository.api.ApiServices
import com.kotlin.mvvm.repository.db.news.NewsArticlesDao
import com.kotlin.mvvm.repository.model.news.NewsArticles
import com.kotlin.mvvm.repository.model.news.NewsSource
import com.kotlin.mvvm.utils.ConnectivityUtil

import javax.inject.Singleton

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Waheed on 04,November,2019
 */

@Singleton
class NewsRepositoryUsingExecutor @Inject
constructor(
    private val context: Context,
    private val apiServices: ApiServices,
    private val newsDao: NewsArticlesDao,
    private val appExecutors: AppExecutors = AppExecutors()
) {
    /**
     *
     */
    fun getNewsArticles(): LiveData<List<NewsArticles>> {
        if (ConnectivityUtil.isConnected(context)) {

            getNewsArticlesFromServer() // try to refresh data if possible from Github Api
        }

        return newsDao.getNewsArticles() // return a LiveData directly from the database.
    }

    // ---
    private fun getNewsArticlesFromServer() {
        appExecutors.networkIO().execute {
            // If user have to be updated
            apiServices.getNewsSourceUsingCall().enqueue(object : Callback<NewsSource> {
                override fun onResponse(call: Call<NewsSource>, response: Response<NewsSource>) {
                    Log.e("TAG", "DATA REFRESHED FROM NETWORK")
                    appExecutors.diskIO().execute {
                        newsDao.insertArticles(response.body()?.articles!!)
                    }
                }

                override fun onFailure(call: Call<NewsSource>, t: Throwable) {
                    Log.e("TAG", "DATA REFRESHED FROM NETWORK")
                }
            })
        }
    }

}
