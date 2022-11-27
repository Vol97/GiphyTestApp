package com.svoloshyn.giphytestapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DownsizedSizeData(
    @SerializedName("height")
    var downsizedHeight: String,
    @SerializedName("width")
    var downsizedWidth: String,
    @SerializedName("url")
    var downsizedUrl: String
): Parcelable