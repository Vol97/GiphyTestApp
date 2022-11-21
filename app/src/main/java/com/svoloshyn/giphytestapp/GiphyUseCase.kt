package com.svoloshyn.giphytestapp

import javax.inject.Inject

interface GiphyUseCase {
    suspend fun getSearchGifs(item: String): List<String>
}

class GiphyUseCaseImpl @Inject constructor(
    private val repository: GiphyRepository
): GiphyUseCase {

    override suspend fun getSearchGifs(item: String): List<String> {
        val generalDataList = repository.getSearchGifs(item)

        val urlList: MutableList<String> = mutableListOf()

        for (gif in generalDataList) {
            urlList.add(gif.images.downsized.url)
        }

        return urlList
    }
}