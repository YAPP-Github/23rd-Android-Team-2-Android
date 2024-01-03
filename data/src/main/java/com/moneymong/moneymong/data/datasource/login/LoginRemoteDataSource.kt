package com.moneymong.moneymong.data.datasource.login


interface LoginRemoteDataSource {
    suspend fun loginWithKakaoTalk()
    suspend fun loginWithKakaoAccount()
}