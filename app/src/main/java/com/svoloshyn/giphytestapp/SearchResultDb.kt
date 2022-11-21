package com.svoloshyn.giphytestapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchResultDb")
data class SearchResultDb(
    @PrimaryKey val searchText: String,
    @ColumnInfo(name = "search_results") val searchResults: List<String>
)
