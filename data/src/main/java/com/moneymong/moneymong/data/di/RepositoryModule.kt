package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.repository.AgencyRepositoryImpl
import com.moneymong.moneymong.domain.repository.AgencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAgencyRepository(
        agencyRepositoryImpl: AgencyRepositoryImpl
    ): AgencyRepository
}