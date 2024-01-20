package com.moneymong.moneymong.data.repository

import com.moneymong.moneymong.data.datasource.UserRemoteDataSource
import com.moneymong.moneymong.data.mapper.user.toEntity
import com.moneymong.moneymong.domain.entity.mymong.UserEntity
import com.moneymong.moneymong.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getMyInfo(): Result<UserEntity> {
        return userRemoteDataSource.getMyInfo().map { it.toEntity() }
    }

    override suspend fun withdrawal(): Result<Unit> {
        return userRemoteDataSource.withdrawal()
    }
}
