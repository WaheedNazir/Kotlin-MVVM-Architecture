package com.kotlin.mvvm.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.repository.repo.countries.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A container for [Country] related data to show on the UI.
 */

@HiltViewModel
class CountriesViewModel @Inject constructor(private val countriesRepository: CountriesRepository) :
    ViewModel() {

    val countries: LiveData<List<Country>> = countriesRepository.getCountries.asLiveData()

    /**
     * If countries are empty in the Database just call this to refresh
     */
    fun refreshCountries() = viewModelScope.launch {
        countriesRepository.insertCountries()
    }
}