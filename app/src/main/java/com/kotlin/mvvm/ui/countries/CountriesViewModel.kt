package com.kotlin.mvvm.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.repository.repo.countries.CountriesRepository
import javax.inject.Inject

/**
 * Created by Waheed on 08,November,2019
 */

/**
 * A container for [Country] related data to show on the UI.
 */
class CountriesViewModel @Inject constructor(countriesRepository: CountriesRepository) :
    ViewModel() {

    /**
     * Loading news articles from internet and database
     */
    private var countries: LiveData<List<Country>> = countriesRepository.getCountries()

    fun getCountries() = countries
}