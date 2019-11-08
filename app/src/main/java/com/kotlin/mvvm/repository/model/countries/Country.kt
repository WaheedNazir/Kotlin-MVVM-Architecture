package com.kotlin.mvvm.repository.model.countries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Created by Waheed on 08,November,2019
 */

/**
 * News Article Model an entity that represents a table and holds schema of News articles
 *
 * By default, Room uses the class name as the database table name. If you want the table to have a different name, set the tableName
 * property of the @Entity annotation, as shown in the following code snippet:
 * @Entity(tableName = "news_table")
 */
@Entity(tableName = "countries_table")
data class Country(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("countryName") var countryName: String? = null,
    @SerializedName("displayName") var displayName: String? = null,
    @SerializedName("countryKey") var countryKey: String? = null,
    @SerializedName("countryFagUrl") var countryFagUrl: String? = null
)