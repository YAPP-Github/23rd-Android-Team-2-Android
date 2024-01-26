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
            .onSuccess {
                // TODO:  탈퇴 성공 시 로컬 토큰 삭제
            }
    }

    override suspend fun logout(): Result<Unit> {
        return userRemoteDataSource.logout()
            .onSuccess {
                // TODO:  로그아웃 성공 시 로컬 토큰 삭제
            }
    }
}
