package com.moneymong.moneymong.data.datasource.login

import com.moneymong.moneymong.network.response.login.RefreshTokenResponse

interface LoginLocalDataSource {
    suspend fun getRefreshToken(): String
    suspend fun getAccessToken(): String
    suspend fun updateTokens(aToken: String, rToken: String)
    suspend fun updateAccessToken(aToken: String)
}