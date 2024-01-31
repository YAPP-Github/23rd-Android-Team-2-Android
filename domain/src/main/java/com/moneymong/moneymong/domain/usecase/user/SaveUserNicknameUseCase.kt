package com.moneymong.moneymong.domain.usecase.user

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.user.UserRepository
import javax.inject.Inject

class SaveUserNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<String, Unit>() {
    override suspend fun invoke(data: String) =
        userRepository.saveUserNickName(data)
}