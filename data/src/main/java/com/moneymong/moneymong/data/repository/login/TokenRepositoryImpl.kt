package com.moneymong.moneymong.data.repository.login

import com.moneymong.moneymong.data.datasource.login.LoginLocalDataSource
import com.moneymong.moneymong.data.datasource.login.TokenRemoteDataSource
import com.moneymong.moneymong.data.mapper.login.toEntity
import com.moneymong.moneymong.data.mapper.login.toRequest
import com.moneymong.moneymong.domain.entity.login.RefreshTokenEntity
import com.moneymong.moneymong.domain.entity.login.UserDataStoreInfoEntity
import com.moneymong.moneymong.domain.param.login.RefreshTokenParam
import com.moneymong.moneymong.domain.repository.TokenRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val localDataSource: LoginLocalDataSource,
    private val tokenRemoteDataSource: TokenRemoteDataSource
) : TokenRepository {
    override val tokenUpdateFailed = MutableSharedFlow<Boolean>(replay = 1)

    override suspend fun notifyTokenUpdateFailed(failed: Boolean) {
        tokenUpdateFailed.emit(failed)
    }

    override suspend fun getRefreshToken(): Result<String> {
        return localDataSource.getRefreshToken()
    }

    override suspend fun getAccessToken(): Result<String> {
        return localDataSource.getAccessToken()
    }

    override suspend fun getDataStoreInfo(): Result<UserDataStoreInfoEntity> {
        return localDataSource.getDataStoreInfo().map { it.toEntity() }
    }

    override suspend fun getUpdateToken(refreshToken: String): Result<RefreshTokenEntity> {
        return tokenRemoteDataSource.getUpdateToken(refreshToken).map { it.toEntity() }
    }

    override suspend fun deleteToken() {
        localDataSource.deleteToken()
    }

    override suspend fun updateTokens(aToken: String, rToken: String) {
        localDataSource.updateTokens(aToken, rToken)
    }

    override suspend fun updateAccessToken(aToken: String) {
        localDataSource.updateAccessToken(aToken)
    }

    override suspend fun deleteRefreshToken(body: RefreshTokenParam) {
        tokenRemoteDataSource.deleteRefreshToken(body.toRequest())
    }

    override suspend fun getSchoolInfo(): Result<Boolean> {
        return localDataSource.getSchoolInfo()
    }

    override suspend fun setSchoolInfoExist(infoExist : Boolean) {
        return localDataSource.setSchoolInfoExist(infoExist)
    }

}