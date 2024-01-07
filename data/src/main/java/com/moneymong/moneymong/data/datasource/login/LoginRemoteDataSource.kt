package com.moneymong.moneymong.data.datasource.login

import com.moneymong.moneymong.network.response.login.RefreshTokenResponse


interface LoginRemoteDataSource {
    suspend fun loginWithKakaoTalk()
    suspend fun loginWithKakaoAccount()

}