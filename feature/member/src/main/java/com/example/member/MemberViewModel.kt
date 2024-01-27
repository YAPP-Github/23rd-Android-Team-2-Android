package com.example.member

import android.provider.ContactsContract.CommonDataKinds.StructuredName
import android.util.Log
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.entity.member.AgencyUserEntity
import com.moneymong.moneymong.domain.param.member.UpdateAuthorParam
import com.moneymong.moneymong.domain.usecase.member.GetMyInfoUseCase
import com.moneymong.moneymong.domain.usecase.member.MemberInvitationCodeUseCase
import com.moneymong.moneymong.domain.usecase.member.MemberListUseCase
import com.moneymong.moneymong.domain.usecase.member.MemberReInvitationCodeUseCase
import com.moneymong.moneymong.domain.usecase.member.UpdateMemberAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val memberInvitationCodeUseCase: MemberInvitationCodeUseCase,
    private val memberReInvitationCodeUseCase: MemberReInvitationCodeUseCase,
    private val memberListUseCase: MemberListUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase,
    private val updateMemberAuthorUseCase: UpdateMemberAuthorUseCase
) : BaseViewModel<MemberState, MemberSideEffect>(MemberState()) {

    fun onVertClickChanged(vertClick: Boolean) = intent {
        reduce {
            state.copy(
                vertClick = vertClick
            )
        }
    }

    fun vertClickedUserIdChanged(userId: Long) = intent {
        reduce {
            state.copy(
                vertClickedUserId = userId
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

    fun memberMyInfoChanged(id: Long, userId: Long, nickname: String, agencyUserRole: String) =
        intent {
            reduce {
                state.copy(
                    memberMyInfoList = AgencyUserEntity(id, userId, nickname, agencyUserRole)
                )
            }
        }

    fun updateFilteredMemberList(memberMyInfoId: Long) = intent {
        val updatedFilteredMemberList =
            state.memberList.filterNot { it.userId == memberMyInfoId }

        reduce {
            state.copy(filteredMemberList = updatedFilteredMemberList)
        }
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

    fun getMemberList(agencyId: Long) = intent {
        memberListUseCase.invoke(agencyId)
            .onSuccess {
                reduce {
                    state.copy(
                        memberList = it.agencyUsers
                    )
                }
            }
            .onFailure {
                //TODO - 에러화면
            }
    }

    fun getMyInfo(data: Unit) = intent {
        getMyInfoUseCase.invoke(Unit)
            .onSuccess {
                reduce {
                    state.copy(
                        memberMyInfoId = it.id
                    )
                }
            }
            .onFailure {
                //TODO - 에러화면
            }
    }

    fun updateMemberAuthor(agencyId: Long, role: String, userId: Long) = intent {
        updateMemberAuthorUseCase.invoke(agencyId, UpdateAuthorParam(role, userId))
            .onSuccess {
                updateFilteredMemberList(userId, role)
                updateMemberList(userId, role)
            }
            .onFailure {
                //TODO - 에러 화면
            }
    }

    private fun updateFilteredMemberList(userId: Long, role: String) = intent {
        val currentFilteredMemberList = state.filteredMemberList
        val updatedFilteredMemberList = currentFilteredMemberList.map { member ->
            if (member.userId == userId) {
                member.copy(agencyUserRole = role)
            } else {
                member
            }
        }
        reduce {
            state.copy(
                filteredMemberList = updatedFilteredMemberList,
                roleChanged = true
            )
        }
    }

    private fun updateMemberList(userId: Long, role: String) = intent {
        val currentMemberList = state.memberList
        val updatedMemberList = currentMemberList.map { member ->
            if (member.userId == userId) {
                member.copy(agencyUserRole = role)
            } else {
                member
            }
        }
        reduce {
            state.copy(
                memberList = updatedMemberList,
            )
        }
    }


}