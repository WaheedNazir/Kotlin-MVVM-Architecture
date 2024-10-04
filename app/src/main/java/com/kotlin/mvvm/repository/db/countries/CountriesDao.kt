package com.kotlin.mvvm.repository.db.countries

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.mvvm.repository.model.countries.Country
import kotlinx.coroutines.flow.Flow

/**
 * Abstracts access to the countries database
 */
@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<Country>)

    @Query("SELECT * FROM countries_table")
    fun getCountries(): Flow<List<Country>>

    @Query("DELETE FROM countries_table")
    suspend fun deleteAllCountries()
}