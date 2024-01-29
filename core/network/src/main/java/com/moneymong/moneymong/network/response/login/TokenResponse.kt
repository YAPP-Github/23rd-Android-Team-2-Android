package com.moneymong.moneymong.network.response.login

data class TokenResponse(
    val accessToken : String,
    val refreshToken : String,
    val loginSuccess : Boolean,
    val schoolInfoExist : Boolean
)
