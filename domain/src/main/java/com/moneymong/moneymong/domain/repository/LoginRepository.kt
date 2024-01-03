package com.moneymong.moneymong.domain.repository

interface LoginRepository {
    suspend fun kakaoLogin()
}