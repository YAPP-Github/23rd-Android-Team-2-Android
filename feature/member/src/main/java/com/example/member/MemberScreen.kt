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
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberScreen() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { true }
    )

    val bottomSheetType = remember { mutableStateOf(BottomSheetType.ROLE_ASSIGNMENT_EXPORT) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val onCopyClick = remember { mutableStateOf(false) }
    val onReissueChange = remember { mutableStateOf(false) }
    val vertClick = remember { mutableStateOf(false) }
    val isStaffChecked = remember { mutableStateOf(false) }
    val isMemberChecked = remember { mutableStateOf(false) }
    val roleChanged = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    fun onIconClick() {
        vertClick.value = true
    }

    fun onCopyChange() {
        onCopyClick.value = true
    }

    fun onReissueChange() {
        onReissueChange.value = true
    }

    if (showDialog.value) {
        MemberDialogView(
            onDismissRequest = {
                showDialog.value = false
            },
            onConfirmation = {
                showDialog.value = false
            }
        )
    }

    if (vertClick.value) {
        isStaffChecked.value = false
        isMemberChecked.value = false
        roleChanged.value = false
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    sheetState.hide()
                    vertClick.value = false
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
                        .padding(horizontal = 20.dp)
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
                                vertClick.value = false
                                showDialog.value = true
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
                        .padding(vertical = 20.dp)
                        .windowInsetsPadding(BottomSheetDefaults.windowInsets)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isStaffChecked.value = !isStaffChecked.value
                                isMemberChecked.value = false
                            }
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "운영진",
                            style = Body4,
                            color = if (isStaffChecked.value) Blue04 else Gray05
                        )
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(start = 253.dp),
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = null,
                            tint = if (isStaffChecked.value) Blue04 else Gray03
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isMemberChecked.value = !isMemberChecked.value
                                isStaffChecked.value = false
                            },
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "일반멤버",
                            style = Body4,
                            color = if (isMemberChecked.value) Blue04 else Gray05
                        )
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(start = 253.dp),
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = null,
                            tint = if (isMemberChecked.value) Blue04 else Gray03
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    MDSButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            roleChanged.value = isStaffChecked.value || isMemberChecked.value
                            bottomSheetType.value = BottomSheetType.ROLE_ASSIGNMENT_EXPORT
                            vertClick.value = false

                        },
                        text = "저장",
                        type = MDSButtonType.PRIMARY,
                        size = MDSButtonSize.LARGE,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }

    // vertClick 상태가 변경될 때 바텀 시트의 상태를 제어
    LaunchedEffect(key1 = vertClick.value) {
        if (vertClick.value) {
            coroutineScope.launch {
                sheetState.show()
            }
        }
    }

    LaunchedEffect(key1 = onCopyClick.value) {
        if (onCopyClick.value) {
            val result = snackbarHostState.showSnackbar(
                message = "초대코드 123456이 복사되었습니다",
                withDismissAction = true
            )
            // 스낵바가 닫히면 onClick 상태를 false로 변경
            if (result == SnackbarResult.Dismissed) {
                onCopyClick.value = false
            }
        }
    }

    LaunchedEffect(key1 = onReissueChange.value) {
        if (onReissueChange.value) {
            val result = snackbarHostState.showSnackbar(
                message = "초대코드가 재발급 되었습니다",
                withDismissAction = true
            )
            // 스낵바가 닫히면 onClick 상태를 false로 변경
            if (result == SnackbarResult.Dismissed) {
                onReissueChange.value = false
            }
        }
    }

    LaunchedEffect(roleChanged.value) {
        if (roleChanged.value) {
            snackbarHostState.showSnackbar(
                message = "역할이 성공적으로 변경됐습니다.",
                withDismissAction = true
            )

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = MMHorizontalSpacing)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "나",
            style = Body3,
            color = Gray07
        )
        MemberCardView(
            modifier = Modifier,
            onCopyChange = { onCopyChange() },
            onReissueChange = { onReissueChange() }
        )

        MemberListView(
            modifier = Modifier.padding(top = 24.dp),
            onIconClick = { onIconClick() }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            MDSSnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(BottomCenter)
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    MemberScreen()
}