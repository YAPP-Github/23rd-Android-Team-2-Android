package com.moneymong.moneymong.domain.entity.member

data class AgencyUserEntity(
    val id: Long,
    val userId: Long,
    val nickname: String,
    val agencyUserRole: String
)