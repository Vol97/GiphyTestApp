package com.svoloshyn.giphytestapp.ui.screens.search

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.svoloshyn.giphytestapp.api.GiphyApi
import com.svoloshyn.giphytestapp.databse.AppDatabase
import com.svoloshyn.giphytestapp.models.GeneralGifData
import com.svoloshyn.giphytestapp.models.GeneralGifDataRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class GiphyRemoteMediator(
    private val api: GiphyApi,
    private val database: AppDatabase,
    private val searchString: String
) : RemoteMediator<Int, GeneralGifData>() {

    private val generalGifDataDao = database.generalGifDataDao()
    private val generalGifDataRemoteKeysDao = database.generalGifDataRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GeneralGifData>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val limit = 15
            val offset = if (currentPage == 1) 0 else limit * currentPage

            val response = api.getSearchGifs(item = searchString, limit = limit, offset = offset)
            val endOfPaginationReached = response.list.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    generalGifDataDao.deleteAllGifs()
                    generalGifDataRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.list.map { generalGifData ->
                    GeneralGifDataRemoteKeys(
                        id = generalGifData.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                generalGifDataRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                generalGifDataDao.addGifs(images = response.list)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, GeneralGifData>
    ): GeneralGifDataRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                generalGifDataRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, GeneralGifData>
    ): GeneralGifDataRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                generalGifDataRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, GeneralGifData>
    ): GeneralGifDataRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                generalGifDataRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }
}