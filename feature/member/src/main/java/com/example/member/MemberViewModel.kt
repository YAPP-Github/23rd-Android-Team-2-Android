package com.example.member

import android.util.Log
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity
import com.moneymong.moneymong.domain.usecase.member.MemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val memberUseCase: MemberUseCase
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

    fun getInvitationCode(agencyId: Long) = intent {
        CoroutineScope(Dispatchers.IO).launch {
            memberUseCase.invoke(agencyId)
                .onSuccess {
                    Log.d("isInvitationCode", it.code)
                    reduce {
                        state.copy(
                            isInvitationCode = it.code
                        )
                    }
                }
                .onFailure {
                    //TODO - 에러화면
                }
        }
    }
}