package com.kotlin.mvvm.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.model.news.News
import com.kotlin.mvvm.repository.repo.news.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * A container for [News] related data to show on the UI.
 */

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    /**
     * Loading news articles from internet and database
     */
    private fun newsArticles(countryKey: String): LiveData<Resource<List<News>?>> =
        newsRepository.getNewsArticles(countryKey)


    fun getNewsArticles(countryKey: String) = newsArticles(countryKey)

    /**
     * Loading news articles from internet only
     */
    private fun newsArticlesFromOnlyServer(countryKey: String) =
        newsRepository.getNewsArticlesFromServerOnly(countryKey)

    fun getNewsArticlesFromServer(countryKey: String) = newsArticlesFromOnlyServer(countryKey)

}