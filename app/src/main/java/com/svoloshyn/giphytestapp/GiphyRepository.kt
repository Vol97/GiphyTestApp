package com.svoloshyn.giphytestapp

import javax.inject.Inject

interface GiphyRepository {
    suspend fun getSearchGifs(item: String): List<GenrealGifData>
}

class GiphyRepositoryImpl @Inject constructor(
    private val api: GiphyApi
): GiphyRepository {
    override suspend fun getSearchGifs(item: String) = api.getSearchGifs(item).list
}