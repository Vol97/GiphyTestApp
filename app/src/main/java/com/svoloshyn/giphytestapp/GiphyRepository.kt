package com.svoloshyn.giphytestapp

import javax.inject.Inject

interface GiphyRepository {
    suspend fun getTrendingGifs(): List<GenrealGifData>
}

class GiphyRepositoryImpl @Inject constructor(
    private val api: GiphyApi
): GiphyRepository {
    override suspend fun getTrendingGifs() = api.getTrendingGifs().list
}