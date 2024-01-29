package com.moneymong.moneymong.domain.entity.member

data class MemberListEntity(
    val agencyUsers: List<AgencyUserEntity>,
    val count: Int
)