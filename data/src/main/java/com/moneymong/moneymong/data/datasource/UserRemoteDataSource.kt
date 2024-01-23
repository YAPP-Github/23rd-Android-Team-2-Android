package com.moneymong.moneymong.data.datasource

import com.moneymong.moneymong.network.response.user.UserResponse

interface UserRemoteDataSource {

    suspend fun getMyInfo(): Result<UserResponse>
}