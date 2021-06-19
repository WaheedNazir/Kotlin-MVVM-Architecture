package com.kotlin.mvvm.repository.repo.countries

import android.content.Context
import androidx.lifecycle.LiveData
import com.kotlin.mvvm.repository.db.countries.CountriesDao
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.utils.CountryNameMapping
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Waheed on 08,November,2019
 */

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 */

@Singleton
class CountriesRepository @Inject constructor(
    private val countriesDao: CountriesDao,
    @ApplicationContext val context: Context
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    suspend fun getCountries(): List<Country> {
        withContext(Dispatchers.IO) {
            val list: List<String>? = getListFromAssets()
            val listOfCountries = ArrayList<Country>()
            list?.forEach { item ->
                val country = Country().apply {
                    countryName = item
                    displayName = getDisplayName(item)
                    countryFagUrl = getFlagUrl(item)
                    countryKey = CountryNameMapping.getCountryKey(item)
                }
                listOfCountries.add(country)
            }
            countriesDao.deleteAllCountries()
            countriesDao.insertCountries(listOfCountries)
        }

        return countriesDao.getCountries()
    }


    private suspend fun getListFromAssets(): List<String>? = withContext(Dispatchers.IO) {
        val asList = context.assets.list("countries")?.asList<String>()
        asList
    }


    private fun getDisplayName(name: String): String =
        name.replace("_", " ").replace(".png", "")


    private fun getFlagUrl(name: String): String = "file:///android_asset/countries/$name"

}