package com.kotlin.mvvm.repository.model.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Created by Waheed on 04,November,2019
 */

/**
 * News Article Model an entity that represents a table and holds schema of News articles
 *
 * By default, Room uses the class name as the database table name. If you want the table to have a different name, set the tableName
 * property of the @Entity annotation, as shown in the following code snippet:
 * @Entity(tableName = "news_table")
 */
@Entity(tableName = "news_table")
data class NewsArticles(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("author") var author: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("urlToImage") var urlToImage: String? = null,
    @SerializedName("publishedAt") var publishedAt: String? = null
)