package com.kotlin.mvvm.di.modules

import com.kotlin.mvvm.ui.countryListing.CountryListingActivity
import com.kotlin.mvvm.ui.newsArticles.NewsArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * All your Activities participating in DI would be listed here.
 */
@Module(includes = [FragmentModule::class]) // Including Fragment Module Available For Activities
abstract class ActivityModule {

    /**
     * Marking Activities to be available to contributes for Android Injector
     */
    @ContributesAndroidInjector
    abstract fun contributeNewsArticlesActivity(): NewsArticlesActivity

    @ContributesAndroidInjector
    abstract fun contributeCountryListingActivity(): CountryListingActivity
}
