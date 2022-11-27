package com.svoloshyn.giphytestapp.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.svoloshyn.giphytestapp.databse.dao.GeneralGifDataDao
import com.svoloshyn.giphytestapp.databse.dao.GeneralGifDataRemoteKeysDao
import com.svoloshyn.giphytestapp.models.GeneralGifData
import com.svoloshyn.giphytestapp.models.GeneralGifDataRemoteKeys

@Database(
    version = 1,
    entities = [GeneralGifData::class, GeneralGifDataRemoteKeys::class]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun generalGifDataDao(): GeneralGifDataDao
    abstract fun generalGifDataRemoteKeysDao(): GeneralGifDataRemoteKeysDao

}