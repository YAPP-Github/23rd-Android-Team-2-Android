package com.moneymong.moneymong.domain.entity.login

data class RefreshTokenEntity(
    val accessToken: String,
    val refreshToken: String
)