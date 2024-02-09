package com.moneymong.moneymong.data.datasource.login

import com.moneymong.moneymong.network.request.login.RefreshTokenRequest
import com.moneymong.moneymong.network.response.login.RefreshTokenResponse

interface TokenRemoteDataSource {
    suspend fun getUpdateToken(refreshToken: String): Result<RefreshTokenResponse>
    suspend fun deleteRefreshToken(body: RefreshTokenRequest)
}