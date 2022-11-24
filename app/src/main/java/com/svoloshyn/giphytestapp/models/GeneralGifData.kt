package com.svoloshyn.giphytestapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeneralGifData(
    @SerializedName("id")
    var id: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("bitly_gif_url")
    var bitlyGifUrl: String,
    @SerializedName("bitly_url")
    var bitlyUrl: String,
    @SerializedName("embed_url")
    var embedUrl: String,
    @SerializedName("rating")
    var rating: String,
    @SerializedName("content_url")
    var contentUrl : String,
    @SerializedName("source_post_url")
    var sourcePostUrl: String,
    @SerializedName("images")
    var images: GifImageData
): Parcelable