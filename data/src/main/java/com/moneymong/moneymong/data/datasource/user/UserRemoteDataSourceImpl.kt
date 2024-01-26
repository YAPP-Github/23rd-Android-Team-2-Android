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
        // todo: change
        return Result.success(Unit)
    }

    override suspend fun logout(): Result<Unit> {
        // todo: change
        return Result.success(Unit)
    }
}