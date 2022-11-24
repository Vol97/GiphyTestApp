//package com.svoloshyn.giphytestapp.database
//
//import androidx.room.Database
//import com.svoloshyn.giphytestapp.database.dao.ExcludedSearchResultsDao
//import com.svoloshyn.giphytestapp.database.dao.SearchResultsDao
//import com.svoloshyn.giphytestapp.database.entities.ExcludedSearchResultsDb
//import com.svoloshyn.giphytestapp.database.entities.SearchResultDb
//
//@Database(
//    version = 1,
//    entities = [SearchResultDb::class, ExcludedSearchResultsDb::class],
//    exportSchema = true
//)
//abstract class AppDatabase {
//    abstract fun searchResultsDao(): SearchResultsDao
//    abstract fun excludedSearchResultsDao(): ExcludedSearchResultsDao
//
//    companion object {
//
//    }
//}