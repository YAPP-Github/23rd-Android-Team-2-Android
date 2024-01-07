package com.moneymong.moneymong.network.api.login

import com.moneymong.moneymong.network.request.signup.TokenRequest
import com.moneymong.moneymong.network.response.login.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccessTokenApi {
    @POST("api/v1/users")
    suspend fun accessTokenApi(
        @Body body : TokenRequest
    ): Result<TokenResponse>
}