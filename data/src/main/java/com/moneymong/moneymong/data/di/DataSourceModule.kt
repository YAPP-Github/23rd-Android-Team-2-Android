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

    @Provides
    fun bindUnivDataSource(
        univRemoteDataSourceImpl: UnivRemoteDataSourceImpl
    ): UnivRemoteDataSource = univRemoteDataSourceImpl

    @Provides
    fun bindLoginLocalDataSource(
        loginLocalDataSourceImpl: LoginLocalDataSourceImpl
    ): LoginLocalDataSource = loginLocalDataSourceImpl

    @Provides
    fun bindTokenRemoteDataSource(
        tokenRemoteDataSourceImpl: TokenRemoteDataSourceImpl
    ) : TokenRemoteDataSource = tokenRemoteDataSourceImpl
}