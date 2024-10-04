package com.kotlin.mvvm.repository.model.countries

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @ColumnInfo(name = "countryName") var countryName: String? = null,
    @ColumnInfo(name = "displayName") var displayName: String? = null,
    @ColumnInfo(name = "countryKey") var countryKey: String? = null,
    @ColumnInfo(name = "countryFagUrl") var countryFagUrl: String? = null
)