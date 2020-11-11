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
import kotlinx.coroutines.Deferred
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
    private val apiServices: ApiServices, private val context: Context
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    suspend fun getNewsArticles(countryShortKey: String): LiveData<Resource<NewsSource>> {
        val data = HashMap<String, String>()
        data["country"] = countryShortKey
        data["apiKey"] = BuildConfig.NEWS_API_KEY

        return object : NetworkAndDBBoundResource<NewsSource, Resource<NewsSource>>() {

            override fun processResponse(response: Resource<NewsSource>): NewsSource =
                response.data!!

            override suspend fun saveCallResult(item: NewsSource) {
                item.articles.let {
                    newsDao.deleteAllArticles()
                    newsDao.insertArticles(it)
                }
            }

            override fun shouldFetch(data: List<News>) = (ConnectivityUtil.isConnected(context))

            override suspend fun loadFromDb(): List<News> = newsDao.getNewsArticles()

            override fun createCallAsync(): Deferred<Resource<NewsSource>> =
                apiServices.getNewsSourceAsync(data)


        }.build().asLiveData()
    }

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     * LiveData<Resource<NewsSource>>
     */
    suspend fun getNewsArticlesFromServerOnly(countryShortKey: String): LiveData<Resource<NewsSource>> {
        val data = HashMap<String, String>()
        data["country"] = countryShortKey
        data["apiKey"] = BuildConfig.NEWS_API_KEY

        return object : NetworkResource<NewsSource>() {

            override fun createCallAsync() = apiServices.getNewsSourceAsync(data)

            override fun processResponse(response: NewsSource): NewsSource = response

            override fun shouldFetch() = (ConnectivityUtil.isConnected(context))

        }.build().asLiveData()
    }

}