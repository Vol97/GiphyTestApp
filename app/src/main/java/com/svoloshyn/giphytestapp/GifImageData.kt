package com.svoloshyn.giphytestapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifImageData(
    @SerializedName("original")
    var original: OriginalSizeData,
    @SerializedName("downsized")
    var downsized: DownsizedSizeData
): Parcelable