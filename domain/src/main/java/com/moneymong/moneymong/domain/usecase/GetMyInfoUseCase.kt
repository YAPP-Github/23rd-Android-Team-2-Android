package com.moneymong.moneymong.domain.usecase

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.mymong.UserEntity
import com.moneymong.moneymong.domain.repository.UserRepository
import javax.inject.Inject

class GetMyInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Unit, Result<UserEntity>>() {

    override suspend fun invoke(data: Unit): Result<UserEntity> {
        return userRepository.getMyInfo()
    }
}