package com.example.member

import com.moneymong.moneymong.common.base.SideEffect

sealed class MemberSideEffect : SideEffect {
    data class GetInvitationCode(val agencyId: Long) : MemberSideEffect()
    data class GetReInvitationCode(val agencyId: Long) : MemberSideEffect()
    data class GetMemberLists(val agencyId: Long) : MemberSideEffect()
    data class GetMyInfo(val data: Unit) : MemberSideEffect()
    data class UpdateMemberAuthor(val agencyId: Long, val role : String, val userId : Long) : MemberSideEffect()
    data class BlockMemberAuthor(val agencyId: Long, val userId: Long) : MemberSideEffect()
}