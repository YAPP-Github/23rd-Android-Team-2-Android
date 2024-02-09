package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.entity.login.RefreshTokenEntity
import com.moneymong.moneymong.domain.entity.login.UserDataStoreInfoEntity
import com.moneymong.moneymong.domain.param.login.RefreshTokenParam
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

interface TokenRepository {
    val tokenUpdateFailed: MutableSharedFlow<Boolean>
    suspend fun notifyTokenUpdateFailed(failed: Boolean)
    suspend fun getRefreshToken(): Result<String>
    suspend fun getAccessToken(): Result<String>
    suspend fun getDataStoreInfo() : Result<UserDataStoreInfoEntity>
    suspend fun getUpdateToken(refreshToken: String): Result<RefreshTokenEntity>
    suspend fun deleteToken()
    suspend fun updateTokens(aToken: String, rToken: String)
    suspend fun updateAccessToken(aToken: String)
    suspend fun deleteRefreshToken(body: RefreshTokenParam)
    suspend fun getSchoolInfo(): Result<Boolean>
    suspend fun setSchoolInfoExist(infoExist : Boolean)
}