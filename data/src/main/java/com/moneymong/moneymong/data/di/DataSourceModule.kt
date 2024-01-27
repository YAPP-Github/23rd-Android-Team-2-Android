package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.datasource.agency.AgencyRemoteDataSource
import com.moneymong.moneymong.data.datasource.mock.AgencyRemoteDataSourceMock
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindAgencyRemoteDataSource(
        agencyRemoteDataSourceMock: AgencyRemoteDataSourceMock
    ): AgencyRemoteDataSource
}