package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.util.LoginCallback

interface LoginRepository {
    suspend fun kakaoLogin(callback: LoginCallback)
}