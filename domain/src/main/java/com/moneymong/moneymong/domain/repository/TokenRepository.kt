package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.param.login.RefreshTokenEntity

interface TokenRepository {
    suspend fun getRefreshToken(): String
    suspend fun getAccessToken(): String
    suspend fun getUpdateToken(refreshToken: String): Result<RefreshTokenEntity>
    suspend fun updateTokens(aToken: String, rToken: String)
    suspend fun updateAccessToken(aToken: String)
}