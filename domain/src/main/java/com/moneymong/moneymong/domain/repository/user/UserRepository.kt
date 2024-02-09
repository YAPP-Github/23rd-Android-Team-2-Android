package com.moneymong.moneymong.domain.repository.user

import com.moneymong.moneymong.domain.entity.user.UserEntity

interface UserRepository {
    

    suspend fun getMyInfo(): Result<UserEntity>

    suspend fun withdrawal(): Result<Unit>

    suspend fun logout(): Result<Unit>

    suspend fun saveUserId(userId: Int)

    suspend fun fetchUserId(): Int

    suspend fun saveUserNickName(nickname: String)

    suspend fun fetchUserNickName(): String
    suspend fun saveDeniedCameraPermission(isDenied: Boolean)
    suspend fun fetchDeniedCameraPermission(): Boolean
}