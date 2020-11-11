package com.kotlin.mvvm.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.repository.model.news.News
import com.kotlin.mvvm.repository.model.news.NewsSource
import com.kotlin.mvvm.repository.repo.news.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * A container for [News] related data to show on the UI.
 */
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    /**
     * Loading news articles from internet and database
     */

    private var news: LiveData<Resource<List<News>>> = MutableLiveData()

    fun getNewsArticles(countryKey: String): LiveData<Resource<List<News>>> {
        viewModelScope.launch {
            val result = newsRepository.getNewsArticles(countryKey)
            news = result
        }
        return news
    }


    private var newsWithOutDb: LiveData<Resource<NewsSource>> = MutableLiveData()

    fun getNewArticlesWithoutDB(countryKey: String): LiveData<Resource<NewsSource>> {
        viewModelScope.launch {
            val result = newsRepository.getNewsArticlesFromServerOnly(countryKey)
            newsWithOutDb = result
        }
        return newsWithOutDb
    }
}