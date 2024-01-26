package com.moneymong.moneymong.domain.usecase.user

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.user.UserEntity
import com.moneymong.moneymong.domain.repository.user.UserRepository
import javax.inject.Inject

class GetMyInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Unit, Result<UserEntity>>() {

    override suspend fun invoke(data: Unit): Result<UserEntity> {
        return userRepository.getMyInfo()
    }
}