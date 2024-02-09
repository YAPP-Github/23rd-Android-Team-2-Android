package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.login.RefreshTokenRequest
import com.moneymong.moneymong.network.request.login.TokenRequest
import com.moneymong.moneymong.network.response.login.RefreshTokenResponse
import com.moneymong.moneymong.network.response.login.TokenResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AccessTokenApi {
    @POST("api/v1/users")
    suspend fun accessTokenApi(
        @Body body : TokenRequest
    ): Result<TokenResponse>

    @POST("api/v1/tokens")
    suspend fun refreshTokenApi(
        @Body body : RefreshTokenRequest
    ) : Result<RefreshTokenResponse>

    @DELETE("api/v1/tokens")
    suspend fun deleteRefreshToken(
        @Body body: RefreshTokenRequest
    )
}