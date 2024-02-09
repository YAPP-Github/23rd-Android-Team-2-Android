package com.moneymong.moneymong.domain.usecase.user

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.user.UserRepository
import javax.inject.Inject

class SaveDeniedCameraPermissionUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Boolean, Unit>() {
    override suspend fun invoke(data: Boolean) =
        userRepository.saveDeniedCameraPermission(data)
}