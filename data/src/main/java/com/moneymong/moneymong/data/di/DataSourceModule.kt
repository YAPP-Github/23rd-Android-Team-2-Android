package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.datasource.login.LoginRemoteDataSource
import com.moneymong.moneymong.data.datasource.login.LoginRemoteDataSourceImpl
import com.moneymong.moneymong.network.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun bindLoginDataSource(
        loginRemoteDataSourceImpl: LoginRemoteDataSourceImpl
    ): LoginRemoteDataSource = loginRemoteDataSourceImpl
}