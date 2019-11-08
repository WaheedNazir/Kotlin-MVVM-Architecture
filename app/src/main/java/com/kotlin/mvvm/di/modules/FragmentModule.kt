package com.kotlin.mvvm.di.modules

import com.kotlin.mvvm.ui.countryListing.CountryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    /**
     * Injecting Fragments
     */
    @ContributesAndroidInjector
    internal abstract fun contributeCountryListFragment(): CountryListFragment
}