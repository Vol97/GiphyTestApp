package com.svoloshyn.giphytestapp.ui.screens.search

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.svoloshyn.giphytestapp.api.GiphyApi
import com.svoloshyn.giphytestapp.databse.AppDatabase
import com.svoloshyn.giphytestapp.models.GeneralGifData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GiphyRepository {
    suspend fun getSearchGifs(
        searchString: String
    ): Flow<PagingData<GeneralGifData>>
}

class GiphyRepositoryImpl @Inject constructor(
    private val api: GiphyApi,
    private val database: AppDatabase
) : GiphyRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getSearchGifs(
        searchString: String
    ): Flow<PagingData<GeneralGifData>> {
        val pagingSourceFactory = { database.generalGifDataDao().getAllGifs() }
        return Pager(
            config = PagingConfig(pageSize = 15),
            remoteMediator = GiphyRemoteMediator(
                api = api,
                database = database,
                searchString = searchString
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}