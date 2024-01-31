package com.moneymong.moneymong.data.datasource.user

import com.moneymong.moneymong.network.response.user.UserResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRemoteDataSourceMock @Inject constructor() : UserRemoteDataSource {

    override suspend fun getMyInfo(): Result<UserResponse> {
        delay(2000)
        return Result.success(
            UserResponse(
                id = 1,
                name = "홍길동",
                email = "hongroaddong@mymong.com",
                university = "길동대학교",
                grade = 4
            )
        )
    }

    override suspend fun withdrawal(): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun logout(refreshToken: String): Result<Unit> {
        return Result.success(Unit)
    }
}