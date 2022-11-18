package com.svoloshyn.giphytestapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrendingGifsResponse(
    @SerializedName("dara")
    var list: List<GenrealGifData>
): Parcelable
