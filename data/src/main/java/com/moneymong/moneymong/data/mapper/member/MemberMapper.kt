package com.moneymong.moneymong.data.mapper.member

import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity
import com.moneymong.moneymong.network.response.member.InvitationCodeResponse

fun InvitationCodeResponse.toEntity() =
    AgencyCodeEntity(
        code = this.code
    )