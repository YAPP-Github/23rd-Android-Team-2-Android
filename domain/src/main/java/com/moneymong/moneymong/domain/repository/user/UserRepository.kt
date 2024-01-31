package com.moneymong.moneymong.domain.repository.user

import com.moneymong.moneymong.domain.entity.user.UserEntity

interface UserRepository {
    

    suspend fun getMyInfo(): Result<UserEntity>

    suspend fun withdrawal(): Result<Unit>

    suspend fun logout(): Result<Unit>
}