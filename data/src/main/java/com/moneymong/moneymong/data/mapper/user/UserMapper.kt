package com.moneymong.moneymong.data.mapper.user

import com.moneymong.moneymong.domain.entity.user.UserEntity
import com.moneymong.moneymong.network.response.user.UserResponse

fun UserResponse.toEntity() = UserEntity(
    id = id,
    name = name,
    email = email,
    university = university,
    grade = grade
)