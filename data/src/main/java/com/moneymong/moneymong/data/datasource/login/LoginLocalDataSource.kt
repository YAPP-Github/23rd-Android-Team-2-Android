package com.moneymong.moneymong.data.datasource.login

import com.moneymong.moneymong.network.response.login.RefreshTokenResponse
import kotlinx.coroutines.flow.Flow

interface LoginLocalDataSource {
    suspend fun getRefreshToken(): String
    suspend fun getAccessToken(): String
    suspend fun deleteToken()
    suspend fun updateTokens(aToken: String, rToken: String)
    suspend fun updateAccessToken(aToken: String)
    suspend fun getSchoolInfo(): Boolean
}