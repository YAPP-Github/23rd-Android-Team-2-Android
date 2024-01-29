package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("api/v1/users/me")
    suspend fun getMyInfo(
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4",
    ): Result<UserResponse>
}