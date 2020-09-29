package com.kotlin.mvvm.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Waheed on 04,November,2019
 */

object ConnectivityUtil {

    @Suppress("DEPRECATION")
    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}