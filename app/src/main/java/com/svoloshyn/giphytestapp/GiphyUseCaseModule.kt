package com.svoloshyn.giphytestapp

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GiphyUseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGiphyUseCase(
        giphyUseCaseImpl: GiphyUseCaseImpl
    ): GiphyUseCase
}