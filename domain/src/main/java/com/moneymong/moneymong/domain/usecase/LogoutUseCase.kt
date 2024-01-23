package com.moneymong.moneymong.domain.usecase

import com.moneymong.moneymong.domain.base.BaseUseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor() : BaseUseCase<Unit, Result<Unit>>() {
    override suspend fun invoke(data: Unit): Result<Unit> {
        return Result.failure(Throwable("로그아웃 실패"))
    }
}