package com.moneymong.moneymong.data.mapper.user

import com.moneymong.moneymong.domain.entity.mymong.UserEntity
import com.moneymong.moneymong.network.response.user.UserResponse

fun UserResponse.toEntity() = UserEntity(
    name = name,
    email = email,
    university = university,
    grade = grade
)