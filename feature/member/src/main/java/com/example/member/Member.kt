package com.example.member

data class Member(
    val id : Int,
    val name : String,
    val type : Int, //예시: 1 - 일반 멤버, 2 - 운영진
)
