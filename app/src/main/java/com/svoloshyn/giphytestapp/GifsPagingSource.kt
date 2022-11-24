package com.svoloshyn.giphytestapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.svoloshyn.giphytestapp.api.GiphyApi
import com.svoloshyn.giphytestapp.models.GeneralGifData

class GifsPagingSource(
    private val giphyApi: GiphyApi,
    private val searchString: String
) : PagingSource<Int, GeneralGifData>() {

    override fun getRefreshKey(state: PagingState<Int, GeneralGifData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GeneralGifData> =
        try {
            val page = params.key ?: 0
            val limit = params.loadSize
            val offset = page * limit
            val data = giphyApi.getSearchGifs(item = searchString, limit = limit, offset = offset)
            if (data.list.isNotEmpty()) {
                LoadResult.Page(
                    data = data.list,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (data.list.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}

