package com.moneymong.moneymong.network.di

import com.moneymong.moneymong.network.source.AgencyRemoteDataSource
import com.moneymong.moneymong.network.source.mock.AgencyRemoteDataSourceMock
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SourceModule {

    @Binds
    fun bindAgencyRemoteDataSource(
        agencyRemoteDataSourceMock: AgencyRemoteDataSourceMock
    ): AgencyRemoteDataSource
}