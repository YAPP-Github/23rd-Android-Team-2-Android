package com.moneymong.moneymong.network.response.member

data class MemberListResponse(
    val agencyUsers: List<AgencyUser>,
    val count: Int
)