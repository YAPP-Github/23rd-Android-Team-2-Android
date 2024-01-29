package com.moneymong.moneymong.domain

interface LoginCallback {
    suspend fun onLoginSuccess()
    suspend fun onLoginFailure(exception: Exception)
}