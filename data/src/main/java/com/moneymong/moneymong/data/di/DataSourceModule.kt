package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.datasource.UserRemoteDataSource
import com.moneymong.moneymong.data.datasource.impl.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindUserRemoteDataSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource
}