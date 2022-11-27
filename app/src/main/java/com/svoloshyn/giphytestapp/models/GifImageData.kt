package com.svoloshyn.giphytestapp.models

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifImageData(
    @SerializedName("original")
    @Embedded
    var original: OriginalSizeData,
    @SerializedName("downsized")
    @Embedded
    var downsized: DownsizedSizeData
): Parcelable