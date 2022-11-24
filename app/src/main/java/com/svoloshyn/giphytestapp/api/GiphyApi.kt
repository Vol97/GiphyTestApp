package com.svoloshyn.giphytestapp.api

import com.svoloshyn.giphytestapp.models.GifsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("search?api_key=KY83IB5yvtohrr8yMQJWBPySRvAeaQVj&")
    suspend fun getSearchGifs(
        @Query("q") item: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String = "g",
        @Query("lang") lang: String = "en"
    ): GifsResponseModel
}