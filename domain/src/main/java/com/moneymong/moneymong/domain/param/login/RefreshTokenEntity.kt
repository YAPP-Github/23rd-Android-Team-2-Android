package com.moneymong.moneymong.domain.param.login

data class RefreshTokenEntity(
    val accessToken: String,
    val refreshToken: String
)