package com.moneymong.moneymong.network.di

import com.moneymong.moneymong.network.util.TokenCallback
import com.moneymong.moneymong.network.util.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TokenModule {
    @Provides
    fun provideTokenCallback(tokenManager: TokenManager): TokenCallback = tokenManager
}

