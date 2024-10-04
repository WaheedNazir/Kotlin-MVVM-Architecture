package com.kotlin.mvvm.repository.repo.countries

import android.content.Context
import androidx.annotation.WorkerThread
import com.kotlin.mvvm.repository.db.countries.CountriesDao
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.utils.CountryNameMapping
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

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

    val getCountries: Flow<List<Country>> = countriesDao.getCountries()

    @WorkerThread
    suspend fun insertCountries() {
        val list: List<String>? = getListFromAssets()
        val listOfCountries = ArrayList<Country>()
        list?.forEach { item ->
            listOfCountries.add(
                Country(
                    countryName = item,
                    displayName = getDisplayName(item),
                    countryFagUrl = getFlagUrl(item),
                    countryKey = CountryNameMapping.getCountryKey(item)
                )
            )
        }
        countriesDao.deleteAllCountries()
        countriesDao.insertCountries(listOfCountries)
    }

    private suspend fun getListFromAssets(): List<String>? = withContext(Dispatchers.IO) {
        val asList = context.assets.list("countries")?.asList<String>()
        asList
    }


    private fun getDisplayName(name: String): String =
        name.replace("_", " ").replace(".png", "")


    private fun getFlagUrl(name: String): String = "file:///android_asset/countries/$name"

}