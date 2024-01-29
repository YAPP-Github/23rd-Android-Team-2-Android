package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.datasource.agency.AgencyRemoteDataSource
import com.moneymong.moneymong.data.datasource.impl.AgencyRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindAgencyRemoteDataSource(
        agencyRemoteDataSourceImpl: AgencyRemoteDataSourceImpl
    ): AgencyRemoteDataSource
}