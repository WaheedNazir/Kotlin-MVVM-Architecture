package com.kotlin.mvvm.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Waheed on 04,November,2019
 */

@Module
class PreferencesModule {

    /**
     * Provides Preferences object with MODE_PRIVATE
     */
    @Provides
    @Singleton
    fun provideSharedPreference(app: Application): SharedPreferences =
        app.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

}
