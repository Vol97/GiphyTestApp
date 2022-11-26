package com.svoloshyn.giphytestapp.ui.screens.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.svoloshyn.giphytestapp.api.GiphyApi
import com.svoloshyn.giphytestapp.models.GeneralGifData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GiphyRepository {
    suspend fun getSearchGifs(
        item: String
    ): Flow<PagingData<GeneralGifData>>
}

class GiphyRepositoryImpl @Inject constructor(
    private val api: GiphyApi
): GiphyRepository {
    override suspend fun getSearchGifs(
        item: String
    ): Flow<PagingData<GeneralGifData>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GifsPagingSource(giphyApi = api, searchString = item)
            }
        ).flow
    }
}