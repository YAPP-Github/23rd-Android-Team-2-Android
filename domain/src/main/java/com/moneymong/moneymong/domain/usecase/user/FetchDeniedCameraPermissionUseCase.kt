package com.moneymong.moneymong.domain.usecase.user

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.user.UserRepository
import javax.inject.Inject

class FetchDeniedCameraPermissionUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Unit, Boolean>() {
    override suspend fun invoke(data: Unit): Boolean =
        userRepository.fetchDeniedCameraPermission()
}