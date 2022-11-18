package com.svoloshyn.giphytestapp

import retrofit2.http.GET

interface GiphyApi {

    @GET("trending?api_key=KY83IB5yvtohrr8yMQJWBPySRvAeaQVj&limit=30&rating=g")
    suspend fun getTrendingGifs(): TrendingGifsResponse
}