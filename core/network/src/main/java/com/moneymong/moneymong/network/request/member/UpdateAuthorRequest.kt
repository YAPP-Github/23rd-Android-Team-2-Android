package com.moneymong.moneymong.network.request.member

data class UpdateAuthorRequest(
    val role: String,
    val userId: Long
)