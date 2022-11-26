package com.svoloshyn.giphytestapp.di

import com.svoloshyn.giphytestapp.ui.screens.search.GiphyRepository
import com.svoloshyn.giphytestapp.ui.screens.search.GiphyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GiphyRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGiphyRepository(
        giphyRepositoryImpl: GiphyRepositoryImpl
    ): GiphyRepository
}