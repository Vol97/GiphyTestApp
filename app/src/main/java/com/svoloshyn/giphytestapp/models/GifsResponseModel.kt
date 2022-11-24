package com.svoloshyn.giphytestapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifsResponseModel(
    @SerializedName("data")
    val list: List<GeneralGifData>
): Parcelable