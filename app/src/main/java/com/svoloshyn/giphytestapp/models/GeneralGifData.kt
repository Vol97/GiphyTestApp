package com.svoloshyn.giphytestapp.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "general_gif_data_table")
data class GeneralGifData(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: String,
    @SerializedName("url")
    var generalUrl: String,
    @SerializedName("images")
    @Embedded
    var images: GifImageData
): Parcelable