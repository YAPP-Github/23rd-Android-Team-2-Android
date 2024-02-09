package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.response.user.UserResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP

interface UserApi {

    @GET("api/v1/users/me")
    suspend fun getMyInfo(): Result<UserResponse>

    @DELETE("api/v1/users/me")
    suspend fun withdrawal(): Result<Unit>

    @HTTP(method = "DELETE", path = "api/v1/tokens", hasBody = true)
    suspend fun logout(
        @Body refreshToken: String
    ): Result<Unit>
}