package com.svoloshyn.giphytestapp

import javax.inject.Inject

interface GiphyUseCase {
    suspend fun getTrendingGifs(): MutableList<String>
}

class GiphyUseCaseImpl @Inject constructor(
    private val repository: GiphyRepository
): GiphyUseCase {
    override suspend fun getTrendingGifs(): MutableList<String> {
        val generalDataList = repository.getTrendingGifs()
        val urlList: MutableList<String> = mutableListOf()

        for (gif in generalDataList) {
            urlList.add(gif.images.original.url)
        }

        return urlList
    }
}
