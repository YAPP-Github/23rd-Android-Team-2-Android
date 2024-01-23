package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.entity.mymong.UserEntity

interface UserRepository {

    suspend fun getMyInfo(): Result<UserEntity>
}