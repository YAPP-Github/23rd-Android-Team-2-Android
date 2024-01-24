package com.example.member

import com.moneymong.moneymong.common.base.SideEffect

sealed class MemberSideEffect : SideEffect {

    data class getInvitationCode(val agencyId: Long) : MemberSideEffect()
    data class getReInvitationCode(val agencyId: Long) : MemberSideEffect()

}