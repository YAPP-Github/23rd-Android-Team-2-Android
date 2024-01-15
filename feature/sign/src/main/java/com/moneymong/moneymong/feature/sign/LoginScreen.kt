package com.moneymong.moneymong.feature.sign

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import com.moneymong.moneymong.design_system.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.feature.sign.navigation.loginRoute
import com.moneymong.moneymong.feature.sign.navigation.signUpRoute
import com.moneymong.moneymong.feature.sign.navigation.splashRoute
import com.moneymong.moneymong.feature.sign.view.KakaoLoginView
import com.moneymong.moneymong.feature.sign.view.TitleView
import com.moneymong.moneymong.feature.sign.viewmodel.LoginViewModel
import com.moneymong.moneymong.home.navigation.homeRoute
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController

) {
    val state = viewModel.collectAsState().value

    LaunchedEffect(key1 = state.isSchoolInfoExist) {
        Log.d("state3", state.isSchoolInfoExist.toString())
        if (state.isSchoolInfoExist == true) {
            navController.navigate(homeRoute) {
                popUpTo(loginRoute) { inclusive = true }
            }
        } else if (state.isSchoolInfoExist == false) {
            navController.navigate(signUpRoute) {
                popUpTo(loginRoute) { inclusive = true }
            }
        }
    }

    LaunchedEffect(key1 = state.isLoginRequired)
    {
        if (state.isLoginRequired == true) {
            navController.navigate(loginRoute) {
                popUpTo(splashRoute) { inclusive = true }
            }
            viewModel.isLoginRequiredChanged(false)
        }
    }

    Scaffold(
        content = { innerPadding ->
            LoginContent(modifier = Modifier.padding(innerPadding))
        }
    )
}


@Composable
private fun LoginContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Blue04)
            .padding(MMHorizontalSpacing)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 236.dp),
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)

        ) {
            KakaoLoginView(modifier = Modifier.fillMaxWidth())
        }
    }
}


