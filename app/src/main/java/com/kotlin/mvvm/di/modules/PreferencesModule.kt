package com.kotlin.mvvm.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Waheed on 04,November,2019
 * Migrated to Hilt 20, June, 2021
 */

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    /**
     * Provides Preferences object with MODE_PRIVATE
     */
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

}
