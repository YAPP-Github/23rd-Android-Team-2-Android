package com.moneymong.moneymong.domain.util

interface LoginCallback {
    suspend fun onLoginSuccess()
    suspend fun onLoginFailure(exception: Exception)
}