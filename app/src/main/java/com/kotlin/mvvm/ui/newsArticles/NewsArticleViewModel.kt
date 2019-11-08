package com.kotlin.mvvm.ui.newsArticles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.model.news.NewsArticles
import com.kotlin.mvvm.repository.model.news.NewsSource
import com.kotlin.mvvm.repository.repo.news.NewsRepositoryUsingBoundResources
import com.kotlin.mvvm.repository.repo.news.NewsRepositoryUsingExecutor
import javax.inject.Inject

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * A container for [NewsArticles] related data to show on the UI.
 */
class NewsArticleViewModel @Inject constructor(
    newsRepository: NewsRepositoryUsingBoundResources,
    userRepository: NewsRepositoryUsingExecutor
) : ViewModel() {

    /**
     * Loading news articles from internet and database
     */
    private var newsArticles: LiveData<Resource<List<NewsArticles>?>> =
        newsRepository.getNewsArticles("us")


    fun getNewsArticles() = newsArticles


    /**
     * Loading news articles from internet only
     */
    private var newsArticlesFromOnlyServer: LiveData<Resource<NewsSource>> =
        newsRepository.getNewsArticlesFromServerOnly("us")

    fun getNewsArticlesFromServer() = newsArticlesFromOnlyServer


    /**
     * Loading news articles from internet and database using Executor
     */
    private var newsArticlesFromServer: LiveData<List<NewsArticles>> =
        userRepository.getNewsArticles()

    fun getNewsArticlesFromServerUsingExecuter() = newsArticlesFromServer
}