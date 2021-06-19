package com.kotlin.mvvm.repository.api

import androidx.lifecycle.LiveData
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.model.news.NewsSource
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Waheed on 04,November,2019
 * Api services to communicate with server
 */

interface ApiServices {
    /**
     * Fetch news articles from Google news using GET API Call on given Url
     * Url would be something like this top-headlines?country=my&apiKey=XYZ
     */
    @GET("top-headlines")
    fun getNewsSource(@QueryMap options: Map<String, String>): LiveData<Resource<NewsSource>>

}
