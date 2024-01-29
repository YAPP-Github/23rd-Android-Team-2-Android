package com.moneymong.moneymong.network.response.signup

data class UniversitiesResponse(
    val universities: List<University>
)

data class University(
    val id: Int,
    val schoolName: String
)