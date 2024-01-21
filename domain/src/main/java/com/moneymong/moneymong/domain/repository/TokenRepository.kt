package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.entity.login.RefreshTokenEntity
import com.moneymong.moneymong.domain.param.login.RefreshTokenParam

interface TokenRepository {
    suspend fun getRefreshToken(): Result<String>
    suspend fun getAccessToken(): Result<String>
    suspend fun getUpdateToken(refreshToken: String): Result<RefreshTokenEntity>
    suspend fun deleteToken()
    suspend fun updateTokens(aToken: String, rToken: String)
    suspend fun updateAccessToken(aToken: String)
    suspend fun deleteRefreshToken(body: RefreshTokenParam)
    suspend fun getSchoolInfo(): Result<Boolean>
}