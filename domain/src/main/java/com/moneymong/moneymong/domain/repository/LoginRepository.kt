package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.LoginCallback

interface LoginRepository {
    suspend fun kakaoLogin(callback: LoginCallback)
}