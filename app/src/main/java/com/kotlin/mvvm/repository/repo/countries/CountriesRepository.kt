package com.kotlin.mvvm.repository.repo.countries

import android.content.Context
import androidx.lifecycle.LiveData
import com.kotlin.mvvm.app.AppExecutors
import com.kotlin.mvvm.repository.db.countries.CountriesDao
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.utils.CountryNameMapping
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
    private val context: Context,
    private val appExecutors: AppExecutors
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    fun getCountries(): LiveData<List<Country>> {

        getCountriesDataFromAssets();

        return countriesDao.getCountries()
    }

    /**
     *
     */
    private fun getCountriesDataFromAssets() {

        val list: List<String> = context.assets.list("countries")?.asList<String>()!!

        val listOfCountries = ArrayList<Country>()

        for (item in list) {
            val country = Country()
            country.countryName = item
            country.displayName = (item.replace("_", " ")
                .replace(".png", ""))
            country.countryFagUrl = "file:///android_asset/countries/$item"
            country.countryKey = CountryNameMapping.getCountryKey(item)
            listOfCountries.add(country)
        }

        appExecutors.diskIO().execute {
            if (listOfCountries.isNotEmpty()) {
                countriesDao.deleteAllCountries()
                countriesDao.insertCountries(listOfCountries)
            }
        }

    }
}