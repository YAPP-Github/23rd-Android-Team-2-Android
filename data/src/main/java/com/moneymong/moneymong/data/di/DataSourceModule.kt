package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.datasource.login.LoginLocalDataSource
import com.moneymong.moneymong.data.datasource.login.LoginLocalDataSourceImpl
import com.moneymong.moneymong.data.datasource.login.LoginRemoteDataSource
import com.moneymong.moneymong.data.datasource.login.LoginRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.login.TokenRemoteDataSource
import com.moneymong.moneymong.data.datasource.login.TokenRemoteDataSourceImpl
import com.moneymong.moneymong.data.datasource.signup.UnivRemoteDataSource
import com.moneymong.moneymong.data.datasource.signup.UnivRemoteDataSourceImpl
import com.moneymong.moneymong.network.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindLoginDataSource(
        loginRemoteDataSourceImpl: LoginRemoteDataSourceImpl
    ): LoginRemoteDataSource

    @Binds
    abstract fun bindUnivDataSource(
        univRemoteDataSourceImpl: UnivRemoteDataSourceImpl
    ): UnivRemoteDataSource

    @Binds
    abstract fun bindLoginLocalDataSource(
        loginLocalDataSourceImpl: LoginLocalDataSourceImpl
    ): LoginLocalDataSource

    @Binds
    abstract fun bindTokenRemoteDataSource(
        tokenRemoteDataSourceImpl: TokenRemoteDataSourceImpl
    ): TokenRemoteDataSource
}
