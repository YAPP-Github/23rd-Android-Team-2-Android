package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.repository.login.LoginRepositoryImpl
import com.moneymong.moneymong.data.repository.login.TokenRepositoryImpl
import com.moneymong.moneymong.data.repository.signup.UnivRepositoryImpl
import com.moneymong.moneymong.domain.repository.LoginRepository
import com.moneymong.moneymong.domain.repository.TokenRepository
import com.moneymong.moneymong.domain.repository.UnivRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    fun bindUnivRepository(
        univRepositoryImpl: UnivRepositoryImpl
    ): UnivRepository

    @Binds
    fun bindTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenRepository
}