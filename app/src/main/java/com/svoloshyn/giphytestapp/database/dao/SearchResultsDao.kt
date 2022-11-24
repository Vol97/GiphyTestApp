//package com.svoloshyn.giphytestapp.database.dao
//
//import androidx.room.*
//import com.svoloshyn.giphytestapp.database.entities.SearchResultDb
//
//@Dao
//interface SearchResultsDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertSearchResult(project: SearchResultDb): Long
//
//    @Query("SELECT * FROM searchResultDb WHERE searchText = :searchText")
//    fun getSearchResult(searchText: String): SearchResultDb
//
//    @Update
//    fun updateSearchResult(projectDb: SearchResultDb)
//
//    @Query("DELETE FROM searchResultDb WHERE searchText = :searchText")
//    fun deleteSearchResult(searchText: String)
//}