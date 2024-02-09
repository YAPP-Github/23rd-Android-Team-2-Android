package com.moneymong.moneymong.network.response.member

data class AgencyUser(
    val id: Long,
    val userId: Long,
    val nickname: String,
    val agencyUserRole: String
)