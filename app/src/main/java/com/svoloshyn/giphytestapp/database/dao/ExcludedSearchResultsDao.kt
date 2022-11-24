//package com.svoloshyn.giphytestapp.database.dao
//
//import androidx.room.*
//import com.svoloshyn.giphytestapp.database.entities.ExcludedSearchResultsDb
//
//@Dao
//interface ExcludedSearchResultsDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertSearchResult(project: ExcludedSearchResultsDb): Long
//
//    @Query("SELECT * FROM excludedSearchResultsDb WHERE searchText = :searchText")
//    fun getSearchResult(searchText: String): ExcludedSearchResultsDb
//
//    @Update
//    fun updateSearchResult(projectDb: ExcludedSearchResultsDb)
//
//    @Query("DELETE FROM excludedSearchResultsDb WHERE searchText = :searchText")
//    fun deleteSearchResult(searchText: String)
//}