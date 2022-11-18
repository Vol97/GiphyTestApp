package com.svoloshyn.giphytestapp

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