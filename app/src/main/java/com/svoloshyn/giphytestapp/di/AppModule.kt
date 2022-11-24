package com.svoloshyn.giphytestapp.di

import android.content.Context
import com.svoloshyn.giphytestapp.api.GiphyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGiphyApi(@ApplicationContext context: Context): GiphyApi {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.giphy.com/v1/gifs/")
            .build()
            .create(GiphyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
}