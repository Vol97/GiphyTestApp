package com.svoloshyn.giphytestapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "excludedSearchResultsDb")
data class ExcludedSearchResultsDb(
    @PrimaryKey val searchText: String,
    @ColumnInfo(name = "excluded_results") val excludedResults: List<String>
)
