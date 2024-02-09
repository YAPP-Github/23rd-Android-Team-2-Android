package com.moneymong.moneymong.domain.usecase.user

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.user.UserRepository
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Int, Unit>() {
    override suspend fun invoke(data: Int) =
        userRepository.saveUserId(data)
}