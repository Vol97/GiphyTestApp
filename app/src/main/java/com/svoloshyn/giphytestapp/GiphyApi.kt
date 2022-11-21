package com.svoloshyn.giphytestapp

import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("search?api_key=KY83IB5yvtohrr8yMQJWBPySRvAeaQVj&")
    suspend fun getSearchGifs(
        @Query("q") item: String,
        @Query("limit") limit: String = "30",
        @Query("offset") offset: String = "0",
        @Query("rating") rating: String = "g",
        @Query("lang") lang: String = "en"
    ): TrendingGifsResponse
}