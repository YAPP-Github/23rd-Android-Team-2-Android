package com.moneymong.moneymong.network.request.agency

data class AgencyJoinRequest(
    val agencyId : Long,
    val invitationCode: String
)