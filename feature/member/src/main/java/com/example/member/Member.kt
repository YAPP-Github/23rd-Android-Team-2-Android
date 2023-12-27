package com.example.member

enum class MemberType {
    GENERAL_MEMBER, // 일반 멤버
    ADMINISTRATOR   // 운영진
}

data class Member(
    val id: Int,
    val name: String,
    val type: MemberType
)