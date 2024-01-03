package com.moneymong.moneymong.data.di

import com.moneymong.moneymong.data.api.login.kakaoLoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    fun provideKakaoLoginApi(retrofit: Retrofit): kakaoLoginApi {
        return retrofit.create(kakaoLoginApi::class.java)
    }

}