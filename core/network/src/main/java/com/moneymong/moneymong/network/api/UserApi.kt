package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("api/v1/users/me")
    suspend fun getMyInfo(): Result<UserResponse>
}