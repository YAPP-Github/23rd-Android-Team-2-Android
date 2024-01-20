package com.moneymong.moneymong.domain.usecase

import com.moneymong.moneymong.domain.base.BaseUseCase
import javax.inject.Inject

class WithdrawalUseCase @Inject constructor() : BaseUseCase<Unit, Result<Unit>>() {
    override suspend fun invoke(data: Unit): Result<Unit> {
        return Result.success(Unit)
    }
}
