package com.moneymong.moneymong.domain.usecase

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Unit, Result<Unit>>() {

    override suspend fun invoke(data: Unit): Result<Unit> {
        return userRepository.logout()
    }
}