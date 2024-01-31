package com.moneymong.moneymong.feature.mymong.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.modal.MDSModal
import com.moneymong.moneymong.design_system.error.ErrorDialog
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.feature.mymong.main.component.MyMongTopBar
import com.moneymong.moneymong.feature.mymong.main.view.MyMongInfoView
import com.moneymong.moneymong.feature.mymong.main.view.MyMongSettingView
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MyMongScreen(
    modifier: Modifier = Modifier,
    viewModel: MyMongViewModel = hiltViewModel(),
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToWithdrawal: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            is MyMongSideEffect.NavigateToWithdrawal -> {
                navigateToWithdrawal()
            }

            is MyMongSideEffect.NavigateToPrivacyPolicy -> {
                navigateToPrivacyPolicy()
            }

            is MyMongSideEffect.NavigateToTermsOfUse -> {
                navigateToTermsOfUse()
            }

            is MyMongSideEffect.NavigateToLogin -> {
                navigateToLogin()
            }
        }
    }

    if (state.visibleLogoutDialog) {
        MDSModal(
            icon = R.drawable.ic_warning_filled,
            title = "정말 로그아웃 하시겠습니까?",
            description = "로그인한 계정이 로그아웃됩니다",
            negativeBtnText = "취소",
            positiveBtnText = "확인",
            onClickNegative = { viewModel.changeLogoutDialogVisibility(false) },
            onClickPositive = viewModel::logout
        )
    }

    if (state.visibleErrorDialog) {
        ErrorDialog(
            message = state.logoutErrorMessage,
            onConfirm = { viewModel.changeErrorDialogVisibility(false) }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Gray01)
            .padding(horizontal = MMHorizontalSpacing),
    ) {
        MyMongTopBar(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(12.dp))
        MyMongInfoView(
            isLoading = state.isInfoLoading,
            isError = state.isInfoError,
            errorMessage = state.infoErrorMessage,
            name = state.name,
            email = state.email,
            university = state.university,
            grade = state.grade,
            getInfo = viewModel::getInfo
        )
        Spacer(modifier = Modifier.height(24.dp))
        MyMongSettingView(
            navigateToTermsOfUse = viewModel::navigateToTermsOfUse,
            navigateToPrivacyPolicy = viewModel::navigateToPriPolicyButton,
            navigateToWithdrawal = viewModel::navigateToWithdrawal,
            showLogoutDialog = { viewModel.changeLogoutDialogVisibility(true) }
        )
    }
}
