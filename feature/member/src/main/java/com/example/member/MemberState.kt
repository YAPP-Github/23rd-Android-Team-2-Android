package com.example.member


import com.moneymong.moneymong.common.base.State

data class MemberState(
    val onCopyClick: Boolean = false,
    val onReissueChange: Boolean = false,
    val vertClick: Boolean = false,
    val isStaffChecked: Boolean = false,
    val isMemberChecked: Boolean = false,
    val roleChanged: Boolean = false,
    val showDialog: Boolean = false,
    val isInvitationCode: String = ""

) : State