package com.kotlin.mvvm.repository.api

import androidx.lifecycle.LiveData
import com.kotlin.mvvm.BuildConfig
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.model.NewsSource
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * Api services to communicate with server
 *
 */
interface ApiServices {

    /**
     * Fetch news articles from Google news using GET API Call on given Url
     */
    @GET("top-headlines?sources=google-news&apiKey=" + BuildConfig.NEWS_API_KEY)
    fun getNewsSource(): LiveData<Resource<NewsSource>>


    /**
     * Fetch news articles from Google news using GET API Call on given Url
     */
    @GET("top-headlines?sources=google-news&apiKey=" + BuildConfig.NEWS_API_KEY)
    fun getNewsSourceUsingCall(): Call<NewsSource>
}
