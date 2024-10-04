package com.kotlin.mvvm.repository.model.news

import androidx.room.ColumnInfo

/**
 * News source model describing the source details
 * and the articles under it.
 */
data class NewsSource(
    @ColumnInfo(name = "status") var status: String = "",
    @ColumnInfo(name = "source") var source: String = "",
    @ColumnInfo(name = "sortBy") var sortBy: String = "",
    @ColumnInfo(name = "articles") var articles: List<News> = emptyList()
)