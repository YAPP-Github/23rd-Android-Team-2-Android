package com.moneymong.moneymong.feature.agency.register

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.modal.MDSModal
import com.moneymong.moneymong.design_system.error.ErrorDialog
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.register.view.AgencyResisterContentView
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AgencyRegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: AgencyRegisterViewModel = hiltViewModel(),
    navigateToComplete: () -> Unit,
    navigateUp: () -> Unit
) {
    val state by viewModel.collectAsState()
    val focusManager = LocalFocusManager.current

    viewModel.collectSideEffect {
        when (it) {
            is AgencyRegisterSideEffect.NavigateToComplete -> {
                navigateToComplete()
            }

            is AgencyRegisterSideEffect.NavigateUp -> {
                navigateUp()
            }
        }
    }

    if (state.visibleOutDialog) {
        MDSModal(
            icon = R.drawable.ic_warning_filled,
            title = "정말 나가시겠습니까?",
            description = "입력하신 내용은 저장되지 않습니다.",
            negativeBtnText = "취소",
            positiveBtnText = "확인",
            onClickNegative = { viewModel.changeOutDialogVisibility(false) },
            onClickPositive = viewModel::navigateUp
        )
    }

    if (state.visibleErrorDialog) {
        ErrorDialog(
            message = state.errorMessage,
            onConfirm = { viewModel.changeErrorDialogVisibility(false) }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
            .pointerInput(key1 = Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
            .padding(horizontal = MMHorizontalSpacing)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = 10.dp)
                .size(24.dp)
                .noRippleClickable(onClick = { viewModel.changeOutDialogVisibility(true) }),
            painter = painterResource(id = R.drawable.ic_close_default),
            tint = Gray07,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        AgencyResisterContentView(
            modifier = Modifier.weight(1f),
            agencyType = state.agencyType,
            onAgencyTypeChange = viewModel::changeAgencyType,
            agencyName = state.agencyName,
            onAgencyNameChange = viewModel::changeAgencyName,
            changeNameTextFieldIsError = viewModel::changeNameTextFieldIsError,
        )
        val canRegister = state.agencyName.text.isNotEmpty() && state.nameTextFieldIsError.not()
        MDSButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp),
            onClick = viewModel::registerAgency,
            text = "등록하기",
            enabled = canRegister
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AgencyRegisterScreenPreview() {
    AgencyRegisterScreen(
        navigateToComplete = {},
        navigateUp = {}
    )
}