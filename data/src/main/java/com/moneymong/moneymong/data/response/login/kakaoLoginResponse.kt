package com.moneymong.moneymong.data.response.login

data class kakaoLoginResponse(
    val accessToken : String,
    val refreshToken : String,
    val loginSuccess : Boolean,
    val schoolInfoExist : Boolean
)
