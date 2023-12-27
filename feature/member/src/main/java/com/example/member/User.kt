package com.example.member


enum class UserType {
    GENERAL_MEMBER, // 일반 멤버
    ADMINISTRATOR   // 운영진
}

data class User(
    val name: String,
    val type: UserType //예시 : 1 - 일반 멤버, 2 - 운영진
)
