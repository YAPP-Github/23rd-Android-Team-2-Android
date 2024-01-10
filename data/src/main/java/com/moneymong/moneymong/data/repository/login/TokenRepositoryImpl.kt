package com.moneymong.moneymong.data.repository.login

import com.moneymong.moneymong.data.datasource.login.LoginLocalDataSource
import com.moneymong.moneymong.data.datasource.login.TokenRemoteDataSource
import com.moneymong.moneymong.data.mapper.login.toEntity
import com.moneymong.moneymong.domain.entity.login.RefreshTokenEntity
import com.moneymong.moneymong.domain.repository.TokenRepository
import com.moneymong.moneymong.network.request.login.RefreshTokenRequest
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val localDataSource: LoginLocalDataSource,
    private val tokenRemoteDataSource: TokenRemoteDataSource
) : TokenRepository {

    override suspend fun getRefreshToken(): String {
        return localDataSource.getRefreshToken()
    }

    override suspend fun getAccessToken(): String {
        return localDataSource.getAccessToken()
    }

    override suspend fun getUpdateToken(refreshToken: String): Result<RefreshTokenEntity> {
        return tokenRemoteDataSource.getUpdateToken(refreshToken).map { it.toEntity() }
    }

    override suspend fun updateTokens(aToken: String, rToken: String) {
        localDataSource.updateTokens(aToken, rToken)
    }

    override suspend fun updateAccessToken(aToken: String) {
        localDataSource.updateAccessToken(aToken)
    }

//    override suspend fun deleteRefreshToken(body: RefreshTokenRequest) {
//        tokenRemoteDataSource.deleteRefreshToken(body)
//    }
}