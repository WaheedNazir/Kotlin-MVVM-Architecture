package com.kotlin.mvvm.di.modules

import com.kotlin.mvvm.ui.newsArticles.NewsArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * All your Activities participating in DI would be listed here.
 */
@Module
abstract class ActivityModule {

    /**
     * Marking NewsArticlesActivity to be available to Contributes for to Android Injector
     */
    @ContributesAndroidInjector
    abstract fun contributeNewsArticlesActivity(): NewsArticlesActivity
}
