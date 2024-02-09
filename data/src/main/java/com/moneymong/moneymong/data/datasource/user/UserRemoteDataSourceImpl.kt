package com.moneymong.moneymong.data.datasource.user

import com.moneymong.moneymong.network.api.UserApi
import com.moneymong.moneymong.network.response.user.UserResponse
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserRemoteDataSource {

    override suspend fun getMyInfo(): Result<UserResponse> {
        return userApi.getMyInfo()
    }

    override suspend fun withdrawal(): Result<Unit> {
        return userApi.withdrawal()
    }

    override suspend fun logout(refreshToken: String): Result<Unit> {
        return userApi.logout(refreshToken)
    }
}