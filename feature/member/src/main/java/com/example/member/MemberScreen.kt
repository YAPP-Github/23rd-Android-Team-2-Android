package com.example.member

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.R
import com.example.member.component.MemberCardView
import com.example.member.component.MemberDialogView
import com.example.member.component.MemberListView
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.component.snackbar.MDSSnackbarHost
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Gray08
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.Red03
import com.moneymong.moneymong.design_system.theme.White
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberScreen(
    viewModel: MemberViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { true }
    )

    val state = viewModel.collectAsState().value
    val bottomSheetType = remember { mutableStateOf(BottomSheetType.ROLE_ASSIGNMENT_EXPORT) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    viewModel.collectSideEffect {
        when (it) {
            is MemberSideEffect.GetInvitationCode -> {
                viewModel.getInvitationCode(it.agencyId)
            }

            is MemberSideEffect.GetReInvitationCode -> {
                viewModel.getReInvitationCode(it.agencyId)
            }

            is MemberSideEffect.GetMemberLists -> {
                viewModel.getMemberLists(it.agencyId)
            }

            is MemberSideEffect.GetMyInfo -> {
                viewModel.getMyInfo(it.data)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.eventEmit(MemberSideEffect.GetInvitationCode(5)) //TODO
        viewModel.eventEmit(MemberSideEffect.GetMemberLists(5)) //TODO
        viewModel.eventEmit(MemberSideEffect.GetMyInfo(Unit)) //TODO - 마이몽 유저 정보 조회 연결
    }


    // vertClick 상태가 변경될 때 바텀 시트의 상태를 제어
    LaunchedEffect(key1 = state.vertClick) {
        if (state.vertClick) {
            coroutineScope.launch {
                sheetState.show()
            }
        }
    }

    LaunchedEffect(key1 = state.onCopyClick) {
        if (state.onCopyClick) {
            val result = snackbarHostState.showSnackbar(
                message = "초대코드 ${state.isInvitationCode}이 복사되었습니다",
                withDismissAction = true
            )
            // 스낵바가 닫히면 onClick 상태를 false로 변경
            if (result == SnackbarResult.Dismissed) {
                viewModel.onCopyClickChanged(false)
            }
        }
    }

    LaunchedEffect(key1 = state.onReissueChange) {
        if (state.onReissueChange) {
            val result = snackbarHostState.showSnackbar(
                message = "초대코드가 재발급 되었습니다",
                withDismissAction = true
            )
            // 스낵바가 닫히면 onClick 상태를 false로 변경
            if (result == SnackbarResult.Dismissed) {
                viewModel.onReissueChanged(false)
            }
        }
    }

    LaunchedEffect(state.roleChanged) {
        if (state.roleChanged) {
            snackbarHostState.showSnackbar(
                message = "역할이 성공적으로 변경됐습니다.",
                withDismissAction = true
            )

        }
    }

    if (state.showDialog) {
        MemberDialogView(
            onDismissRequest = {
                viewModel.onShowDialogChanged(false)
            },
            onConfirmation = {
                viewModel.onShowDialogChanged(false)
            }
        )
    }

    if (state.vertClick) {
        viewModel.isStaffCheckedChanged(false)
        viewModel.isMemberCheckedChanged(false)
        viewModel.isRoleChanged(false)

        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    sheetState.hide()
                    viewModel.onVertClickChanged(false)
                    bottomSheetType.value = BottomSheetType.ROLE_ASSIGNMENT_EXPORT
                }
            },
            modifier = Modifier,
            sheetState = sheetState,
            shape = MaterialTheme.shapes.large,
            containerColor = White,
            tonalElevation = 8.dp,
            scrimColor = Black.copy(alpha = 0.5f),
            dragHandle = null,
            windowInsets = BottomSheetDefaults.windowInsets
        ) {

            if (bottomSheetType.value == BottomSheetType.ROLE_ASSIGNMENT_EXPORT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 24.dp)
                        .windowInsetsPadding(BottomSheetDefaults.windowInsets)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .clickable {
                                bottomSheetType.value = BottomSheetType.ADMIN_GENERAL_MEMBER
                            }
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .height(24.dp),
                            text = "역할 지정",
                            style = Body4,
                            color = Gray08
                        )
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.ic_chevron_right),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onVertClickChanged(false)
                                viewModel.onShowDialogChanged(true)
                            },
                        text = "내보내기",
                        style = Body4,
                        color = Red03
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 24.dp)
                        .windowInsetsPadding(BottomSheetDefaults.windowInsets)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.isStaffCheckedChanged(!state.isStaffChecked)
                                viewModel.isMemberCheckedChanged(false)
                            }
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "운영진",
                            style = Body4,
                            color = if (state.isStaffChecked) Blue04 else Gray05
                        )
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = null,
                            tint = if (state.isStaffChecked) Blue04 else Gray03
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.isMemberCheckedChanged(!state.isMemberChecked)
                                viewModel.isStaffCheckedChanged(false)
                            },
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "일반멤버",
                            style = Body4,
                            color = if (state.isMemberChecked) Blue04 else Gray05
                        )
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = null,
                            tint = if (state.isMemberChecked) Blue04 else Gray03
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    MDSButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            val boolean = state.isStaffChecked || state.isMemberChecked
                            viewModel.isRoleChanged(boolean)
                            bottomSheetType.value = BottomSheetType.ROLE_ASSIGNMENT_EXPORT
                            viewModel.onVertClickChanged(false)
                        },
                        text = "저장",
                        type = MDSButtonType.PRIMARY,
                        size = MDSButtonSize.LARGE,
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = MMHorizontalSpacing)
    ) {
        Text(
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            text = "나",
            style = Body3,
            color = Gray07
        )
        MemberCardView(
            modifier = Modifier,
            memberList = state.memberList,
            memberMyInfoId = state.memberMyInfoId,
            memberMyInfo = state.memberMyInfo,
            memberMyInfoChanged = { id, userId, nickname, agencyUserRole ->
                viewModel.memberMyInfoChanged(
                    id,
                    userId,
                    nickname,
                    agencyUserRole
                )
            },
            invitationCode = state.isInvitationCode,
            isReInvitationCode = { viewModel.eventEmit(MemberSideEffect.GetReInvitationCode(it)) }, //TODO
            onCopyChange = { onCopyClick -> viewModel.onCopyClickChanged(onCopyClick) },
        )

        MemberListView(
            modifier = Modifier.padding(top = 24.dp),
            memberList = state.memberList,
            memberMyInfoId = state.memberMyInfoId,
            filteredMemberList = state.filteredMemberList,
            onIconClick = { vertClick -> viewModel.onVertClickChanged(vertClick) },
            updateFilteredMemberList = { memberMyInfoId ->
                viewModel.updateFilteredMemberList(
                    memberMyInfoId
                )
            },
            )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            MDSSnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(BottomCenter)
                    .padding(bottom = 12.dp)
            )
        }
    }
}
