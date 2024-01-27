package com.moneymong.moneymong.data.mapper.agency

import com.moneymong.moneymong.domain.entity.agency.AgencyJoinEntity
import com.moneymong.moneymong.domain.param.agency.AgencyJoinParam
import com.moneymong.moneymong.network.request.agency.AgencyJoinRequest
import com.moneymong.moneymong.network.response.agency.AgencyJoinResponse

fun AgencyJoinParam.toRequest() = AgencyJoinRequest(
    invitationCode = this.invitationCode
)

fun AgencyJoinResponse.toEntity() = AgencyJoinEntity(
    certified = this.certified
)