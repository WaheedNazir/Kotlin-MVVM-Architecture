package com.kotlin.mvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.mvvm.di.base.ViewModelFactory
import com.kotlin.mvvm.ui.countries.CountriesViewModel
import com.kotlin.mvvm.ui.news.NewsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Created by Waheed on 04,November,2019
 */

@Module
abstract class ViewModelModule {
    /**
     * Binding NewsArticleViewModel using this key "NewsArticleViewModel::class"
     * So you can get NewsArticleViewModel using "NewsArticleViewModel::class" key from factory
     */
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsArticleViewModel(newsViewModel: NewsViewModel): ViewModel

    /**
     * Countries List View Model
     */
    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    abstract fun bindCountriesViewModel(countriesViewModel: CountriesViewModel): ViewModel

    /**
     * Binds ViewModels factory to provide ViewModels.
     */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
