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
    navigateToPrivacyPolicy: () -> Unit
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            is MyMongSideEffect.NavigateToWithdrawal -> Unit // todo

            is MyMongSideEffect.NavigateToPrivacyPolicy -> {
                navigateToPrivacyPolicy()
            }

            is MyMongSideEffect.NavigateToTermsOfUse -> {
                navigateToTermsOfUse()
            }
        }
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
            name = state.name,
            email = state.email,
            university = state.university,
            grade = state.grade
        )
        Spacer(modifier = Modifier.height(24.dp))
        MyMongSettingView(
            navigateToTermsOfUse = viewModel::onClickTermsOfUse,
            navigateToPrivacyPolicy = viewModel::onClickPriPolicyButton,
            navigateToWithdrawal = viewModel::onClickWithdrawal
        )
    }
}
