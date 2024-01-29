package com.moneymong.moneymong.data.datasource.login

import com.moneymong.moneymong.domain.util.LoginCallback


interface LoginRemoteDataSource {
    suspend fun loginWithKakaoTalk(callback: LoginCallback)
    suspend fun loginWithKakaoAccount(callback: LoginCallback)
}