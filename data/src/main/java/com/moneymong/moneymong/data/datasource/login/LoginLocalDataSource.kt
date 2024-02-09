package com.moneymong.moneymong.data.datasource.login

import com.moneymong.moneymong.network.response.login.UserDataStoreInfoResponse

interface LoginLocalDataSource {
    suspend fun getRefreshToken(): Result<String>
    suspend fun getAccessToken(): Result<String>
    suspend fun getDataStoreInfo() : Result<UserDataStoreInfoResponse>
    suspend fun deleteToken()
    suspend fun updateTokens(aToken: String, rToken: String)
    suspend fun updateAccessToken(aToken: String)
    suspend fun getSchoolInfo(): Result<Boolean>
    suspend fun setSchoolInfoExist(infoExist: Boolean)
}