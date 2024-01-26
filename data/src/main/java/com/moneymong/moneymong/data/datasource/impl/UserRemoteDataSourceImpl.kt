package com.moneymong.moneymong.data.datasource.impl

import com.moneymong.moneymong.data.datasource.UserRemoteDataSource
import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.response.user.UserResponse
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val moneyMongApi: MoneyMongApi
) : UserRemoteDataSource {

    override suspend fun getMyInfo(): Result<UserResponse> {
        return moneyMongApi.getMyInfo()
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