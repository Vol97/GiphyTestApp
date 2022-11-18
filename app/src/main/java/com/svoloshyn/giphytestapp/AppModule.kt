package com.svoloshyn.giphytestapp

import android.content.Context
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
            .client(provideOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.giphy.com/v1/gifs/")
            .build()
            .create(GiphyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =  OkHttpClient.Builder()
        .build()
}