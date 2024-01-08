package com.moneymong.moneymong.network.request.login

data class TokenRequest (
    val provider : String,
    val accessToken : String
)