package com.kotlin.mvvm.repository.repo.news

import android.content.Context
import androidx.lifecycle.LiveData
import com.kotlin.mvvm.BuildConfig
import com.kotlin.mvvm.app.AppExecutors
import com.kotlin.mvvm.repository.api.ApiServices
import com.kotlin.mvvm.repository.api.network.NetworkAndDBBoundResource
import com.kotlin.mvvm.repository.api.network.NetworkResource
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.db.news.NewsDao
import com.kotlin.mvvm.repository.model.news.News
import com.kotlin.mvvm.repository.model.news.NewsSource
import com.kotlin.mvvm.utils.ConnectivityUtil
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 */

@Singleton
class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val apiServices: ApiServices, private val context: Context,
    private val appExecutors: AppExecutors = AppExecutors()
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    fun getNewsArticles(countryShortKey: String): LiveData<Resource<List<News>?>> {
        val data = HashMap<String, String>()
        data["country"] = countryShortKey
        data["apiKey"] = BuildConfig.NEWS_API_KEY

        return object : NetworkAndDBBoundResource<List<News>, NewsSource>(appExecutors) {
            override fun saveCallResult(item: NewsSource) {
                if (item.articles.isNotEmpty()) {
                    newsDao.deleteAllArticles()
                    newsDao.insertArticles(item.articles)
                }
            }

            override fun shouldFetch(data: List<News>?) =
                (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = newsDao.getNewsArticles()

            override fun createCall() =
                apiServices.getNewsSource(data)

        }.asLiveData()
    }

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     * LiveData<Resource<NewsSource>>
     */
    fun getNewsArticlesFromServerOnly(countryShortKey: String):
            LiveData<Resource<NewsSource>> {

        val data = HashMap<String, String>()
        data["country"] = countryShortKey
        data["apiKey"] = BuildConfig.NEWS_API_KEY

        return object : NetworkResource<NewsSource>() {
            override fun createCall(): LiveData<Resource<NewsSource>> {
                return apiServices.getNewsSource(data)
            }

        }.asLiveData()
    }

}