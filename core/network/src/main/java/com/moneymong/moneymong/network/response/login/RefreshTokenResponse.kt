package com.moneymong.moneymong.network.response.login

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
)