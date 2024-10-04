package com.kotlin.mvvm.repository.api

import androidx.lifecycle.LiveData
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.model.news.NewsSource
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

/**
 * Created by Waheed
 * Api services to communicate with server
 */

interface ApiServices {
    /**
     * Fetch news articles from Google news using GET API Call on given Url
     * Url would be something like this top-headlines?country=my&apiKey=XYZ
     * GET https://newsapi.org/v2/everything?q=Apple&from=2024-10-03&sortBy=popularity&apiKey=API_KEY
     * GET https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY
     *
     * Response{protocol=h2, code=429, message=, url=https://newsapi.org/v2/top-headlines?country=ar&apiKey=28679d41d4454bffaf6a4f40d4b024cc}
     */
    @GET("top-headlines")
    fun getNewsSource(
        @QueryMap options: Map<String, String>
    ): LiveData<Resource<NewsSource>>

}
