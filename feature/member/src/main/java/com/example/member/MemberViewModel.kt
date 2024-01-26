package com.example.member

import android.provider.ContactsContract.CommonDataKinds.StructuredName
import android.util.Log
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.entity.member.AgencyUserEntity
import com.moneymong.moneymong.domain.usecase.member.GetMyInfoUseCase
import com.moneymong.moneymong.domain.usecase.member.MemberInvitationCodeUseCase
import com.moneymong.moneymong.domain.usecase.member.MemberListUseCase
import com.moneymong.moneymong.domain.usecase.member.MemberReInvitationCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val memberInvitationCodeUseCase: MemberInvitationCodeUseCase,
    private val memberReInvitationCodeUseCase: MemberReInvitationCodeUseCase,
    private val memberListUseCase: MemberListUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase
) : BaseViewModel<MemberState, MemberSideEffect>(MemberState()) {

    fun onVertClickChanged(vertClick: Boolean) = intent {
        reduce {
            state.copy(
                vertClick = vertClick
            )
        }
    }

    fun onCopyClickChanged(onCopyClick: Boolean) = intent {
        reduce {
            state.copy(
                onCopyClick = onCopyClick
            )
        }
    }

    fun onReissueChanged(onReissueChange: Boolean) = intent {
        reduce {
            state.copy(
                onReissueChange = onReissueChange
            )
        }
    }

    fun onShowDialogChanged(showDialog: Boolean) = intent {
        reduce {
            state.copy(
                showDialog = showDialog
            )
        }
    }

    fun isStaffCheckedChanged(isStaffChecked: Boolean) = intent {
        reduce {
            state.copy(
                isStaffChecked = isStaffChecked
            )
        }
    }

    fun isMemberCheckedChanged(isMemberChecked: Boolean) = intent {
        reduce {
            state.copy(
                isMemberChecked = isMemberChecked
            )
        }
    }

    fun isRoleChanged(roleChanged: Boolean) = intent {
        reduce {
            state.copy(
                roleChanged = roleChanged
            )
        }
    }

    fun memberMyInfoChanged(id : Long, userId : Int, nickname: String, agencyUserRole: String ) = intent{
        reduce {
            state.copy(
                memberMyInfoList = AgencyUserEntity(id, userId, nickname, agencyUserRole)
            )
        }
    }

    fun updateFilteredMemberList(memberMyInfoId : Long ) = intent {
        val updatedFilteredMemberList = state.memberList.filterNot { it.userId.toLong() == memberMyInfoId }

        reduce {
            state.copy(filteredMemberList = updatedFilteredMemberList)
        }
        Log.d("filteredMemberList" , state.filteredMemberList.toString())
    }

    fun getInvitationCode(agencyId: Long) = intent {
        memberInvitationCodeUseCase.invoke(agencyId)
            .onSuccess {
                reduce {
                    state.copy(
                        isInvitationCode = it.code
                    )
                }
                Log.d("invitationCode", state.isInvitationCode)

            }.onFailure {
                //TODO - 에러화면
            }
    }

    fun getReInvitationCode(agencyId: Long) = intent {
        memberReInvitationCodeUseCase.invoke(agencyId)
            .onSuccess {
                reduce {
                    state.copy(
                        isInvitationCode = it.code,
                        onReissueChange = true
                    )
                }
            }
            .onFailure {
                //TODO - 에러화면
            }
    }
    private val mockAgencyUsers = listOf(
        AgencyUserEntity(id = 2, userId = 4, nickname = "기은서", agencyUserRole = "STAFF"),
        AgencyUserEntity(id = 1, userId = 3, nickname = "김기서", agencyUserRole = "STAFF"),
        AgencyUserEntity(id = 3, userId = 5, nickname = "장희직", agencyUserRole = "STAFF"),
        AgencyUserEntity(id = 4, userId = 6, nickname = "안병헌", agencyUserRole = "MEMBER"),
        AgencyUserEntity(id = 5, userId = 7, nickname = "김세현", agencyUserRole = "MEMBER"),
        AgencyUserEntity(id = 6, userId = 8, nickname = "구나연", agencyUserRole = "MEMBER"),
        AgencyUserEntity(id = 7, userId = 9, nickname = "김도하", agencyUserRole = "MEMBER"),
        AgencyUserEntity(id = 8, userId = 10, nickname = "한규리", agencyUserRole = "MEMBER"),
        AgencyUserEntity(id = 9, userId = 11, nickname = "임가은", agencyUserRole = "MEMBER"),
        AgencyUserEntity(id = 10, userId = 12, nickname = "김도하", agencyUserRole = "MEMBER"),
        AgencyUserEntity(id = 11, userId = 13, nickname = "한규리", agencyUserRole = "MEMBER"),
        AgencyUserEntity(id = 12, userId = 14, nickname = "임가은", agencyUserRole = "MEMBER"),
    )
    fun getMemberList(agencyId: Long) = intent {
        memberListUseCase.invoke(agencyId)
            .onSuccess {
                reduce {
                    state.copy(
//                        memberList = it.agencyUsers
                        memberList = mockAgencyUsers

                    )
                }
            }
            .onFailure {
                //TODO - 에러화면
            }
    }

    fun getMyInfo(data : Unit) = intent{
        getMyInfoUseCase.invoke(Unit)
            .onSuccess {
                reduce {
                    state.copy(
                        memberMyInfoId = it.id
                    )
                }
                Log.d("memberMyInfoID", state.memberMyInfoId.toString())
            }
            .onFailure {
                //TODO - 에러화면
            }
    }

}