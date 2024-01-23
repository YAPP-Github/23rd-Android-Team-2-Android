package com.moneymong.moneymong.data.datasource.mock

import com.moneymong.moneymong.data.datasource.UserRemoteDataSource
import com.moneymong.moneymong.network.response.user.UserResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRemoteDataSourceMock @Inject constructor() : UserRemoteDataSource {

    override suspend fun getMyInfo(): Result<UserResponse> {
        delay(2000)  // for test
        return Result.success(
            UserResponse(
                name = "홍길동",
                email = "hongroaddong@mymong.com",
                university = "길동대학교",
                grade = 4
            )
        )
//        return Result.failure(Throwable("테스트 에러"))
    }

    override suspend fun withdrawal(): Result<Unit> {
        return Result.success(Unit)
//        return Result.failure(Throwable("테스트 에러"))
    }

    override suspend fun logout(): Result<Unit> {
        return Result.success(Unit)
//        return Result.failure(Throwable("테스트 에러"))
    }
}