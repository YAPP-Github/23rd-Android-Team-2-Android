package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.datasource.UserRemoteDataSource
import com.moneymong.moneymong.data.datasource.mock.UserRemoteDataSourceMock
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindAgencyRemoteDataSource(
        userRemoteDataSourceMock: UserRemoteDataSourceMock
    ): UserRemoteDataSource
}