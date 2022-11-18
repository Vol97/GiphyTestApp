package com.svoloshyn.giphytestapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OriginalSizeData(
    @SerializedName("height")
    var height: String,
    @SerializedName("width")
    var width: String,
    @SerializedName("url")
    var url: String
): Parcelable