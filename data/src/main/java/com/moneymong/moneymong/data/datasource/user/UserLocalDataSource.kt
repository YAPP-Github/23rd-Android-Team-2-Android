package com.moneymong.moneymong.data.datasource.user

interface UserLocalDataSource {
    suspend fun saveUserId(userId: Int)
    suspend fun fetchUserId(): Int
}