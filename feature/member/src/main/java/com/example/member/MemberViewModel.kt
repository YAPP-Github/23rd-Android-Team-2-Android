package com.example.member

import android.util.Log
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.member.MemberInvitationCodeUseCase
import com.moneymong.moneymong.domain.usecase.member.MemberReInvitationCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val memberInvitationCodeUseCase: MemberInvitationCodeUseCase,
    private val memberReInvitationCodeUseCase: MemberReInvitationCodeUseCase
) : BaseViewModel<MemberState, MemberSideEffect>(MemberState()) {

    fun onVertClickChanged(vertClick: Boolean) = intent {
        reduce {
            state.copy(
                visibleBottomSheet = vertClick
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

    fun getInvitationCode(agencyId: Long) = intent {
        CoroutineScope(Dispatchers.IO).launch {
            memberInvitationCodeUseCase.invoke(agencyId)
                .onSuccess {
                    Log.d("invitationCode", it.code)
                    reduce {
                        state.copy(
                            invitationCode = it.code
                        )
                    }
                }
                .onFailure {
                    //TODO - 에러화면
                }
        }
    }

    fun getReInvitationCode(agencyId: Long) = intent{
        CoroutineScope(Dispatchers.IO).launch {
            memberReInvitationCodeUseCase.invoke(agencyId)
                .onSuccess {
                    Log.d("isReInvitationCode", it.code)
                    reduce {
                        state.copy(
                            invitationCode = it.code,
                            onReissueChange = true
                        )
                    }
                }
                .onFailure {
                    //TODO - 에러화면
                }
        }
    }
}