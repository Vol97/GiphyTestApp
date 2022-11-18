package com.svoloshyn.giphytestapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenrealGifData(
    @SerializedName("type")
    var type: String,
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
    @SerializedName("username")
    var source: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("rating")
    var rating: String,
    @SerializedName("content_url")
    var contentUrl : String,
    @SerializedName("source_tld")
    var sourceTld: String,
    @SerializedName("source_post_url")
    var sourcePostUrl: String,
    @SerializedName("images")
    var images: GifImageData
): Parcelable