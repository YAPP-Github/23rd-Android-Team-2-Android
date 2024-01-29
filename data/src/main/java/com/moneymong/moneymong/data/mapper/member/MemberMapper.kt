package com.moneymong.moneymong.data.mapper.member

import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity
import com.moneymong.moneymong.domain.entity.member.AgencyUserEntity
import com.moneymong.moneymong.domain.entity.member.MemberListEntity
import com.moneymong.moneymong.domain.param.member.MemberBlockParam
import com.moneymong.moneymong.domain.param.member.UpdateAuthorParam
import com.moneymong.moneymong.network.request.member.MemberBlockRequest
import com.moneymong.moneymong.network.request.member.UpdateAuthorRequest
import com.moneymong.moneymong.network.response.member.AgencyUser
import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import com.moneymong.moneymong.network.response.member.MemberListResponse

fun InvitationCodeResponse.toEntity() =
    AgencyCodeEntity(
        code = this.code
    )


fun MemberListResponse.toEntity() = MemberListEntity(
    agencyUsers = this.agencyUsers.map { it.toEntity() },
    count = this.count
)

fun AgencyUser.toEntity() = AgencyUserEntity(
    id = this.id,
    userId = this.userId,
    nickname = this.nickname,
    agencyUserRole = this.agencyUserRole
)

fun UpdateAuthorParam.toRequest() = UpdateAuthorRequest(
    role = this.role,
    userId = this.userId
)

fun MemberBlockParam.toRequest() = MemberBlockRequest(
    userId = this.userId
)