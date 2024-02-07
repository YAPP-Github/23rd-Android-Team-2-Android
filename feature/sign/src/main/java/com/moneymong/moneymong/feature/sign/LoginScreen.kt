package com.moneymong.moneymong.feature.sign

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import com.moneymong.moneymong.design_system.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.error.ErrorScreen
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.feature.sign.view.KakaoLoginView
import com.moneymong.moneymong.feature.sign.view.TitleView
import com.moneymong.moneymong.feature.sign.viewmodel.LoginViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun LoginScreen(
    navigateToSignup: () -> Unit,
    navigateToLedger: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),

    ) {
    val state = viewModel.collectAsState().value

    LaunchedEffect(key1 = state.isSchoolInfoExist) {
        if (state.isSchoolInfoExist == true) {
            navigateToLedger()
        } else if (state.isSchoolInfoExist == false) {
            navigateToSignup()
            viewModel.isSchoolInfoExistChanged(null)
        }
    }

    LaunchedEffect(key1 = state.isLoginRequired)
    {
        if (state.isLoginRequired == true ) {
            navigateToLogin()
            viewModel.isLoginRequiredChanged(false)
        }
    }

    if (state.visibleError == true ) {
        ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            message = state.errorMessage,
            onRetry = { viewModel.visibleErrorChanged(false) }
        )
    } else if(state.visibleError == false ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Blue04)
                .padding(start = MMHorizontalSpacing, end = MMHorizontalSpacing, bottom = 28.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(320.dp, 162.dp),
                    painter = painterResource(id = R.drawable.img_login_mymong),
                    contentDescription = null
                )
                TitleView("교내 회계관리를 편리하게", "수기 기록은 이제 그만! 간단하게 기록해요.")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                KakaoLoginView(
                    modifier = Modifier.fillMaxWidth(),
                    onLoginButtonClicked = { viewModel.onLoginButtonClicked() }
                )
            }
        }
    }
}
