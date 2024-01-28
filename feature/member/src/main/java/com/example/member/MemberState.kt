package com.example.member

import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.domain.entity.member.AgencyUserEntity

data class MemberState(
    val onCopyClick: Boolean = false,
    val onReissueChange: Boolean = false,
    val vertClick: Boolean = false,
    val isStaffChecked: Boolean = false,
    val isMemberChecked: Boolean = false,
    val roleChanged: Boolean = false,
    val showDialog: Boolean = false,
    val isInvitationCode: String = "",
    val memberList: List<AgencyUserEntity> = emptyList(),
    val memberMyInfoId: Long = 0,
    val memberMyInfo: AgencyUserEntity = AgencyUserEntity(
        id = 0L,
        userId = 0,
        nickname = "",
        agencyUserRole = ""
    ),
    val filteredMemberList: List<AgencyUserEntity> = emptyList()
) : State