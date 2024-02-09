package com.moneymong.moneymong.network.response.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: Long,
    @SerializedName("nickname")
    val name: String,
    val email: String,
    @SerializedName("universityName")
    val university: String?,
    val grade: Int
)