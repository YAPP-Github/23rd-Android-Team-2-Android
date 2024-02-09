package com.moneymong.moneymong.domain.entity.user

data class UserEntity(
    val id: Long,
    val name: String,
    val email: String,
    val university: String?,
    val grade: Int
)