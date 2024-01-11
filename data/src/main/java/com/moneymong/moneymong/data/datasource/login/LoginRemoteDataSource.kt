package com.moneymong.moneymong.data.datasource.login

import com.moneymong.moneymong.domain.LoginCallback
import com.moneymong.moneymong.network.response.login.RefreshTokenResponse


interface LoginRemoteDataSource {
    suspend fun loginWithKakaoTalk(callback: LoginCallback)
    suspend fun loginWithKakaoAccount(callback: LoginCallback)
}