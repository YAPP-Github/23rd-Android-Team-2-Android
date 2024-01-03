package com.moneymong.moneymong.feature.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.feature.sign.view.KakaoLoginView
import com.moneymong.moneymong.feature.sign.view.TitleView



@Composable
fun LoginScreen() {
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
            .background(color = Color.White)
            .padding(MMHorizontalSpacing)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            TitleView("교내 회계관리를 편리하게\n시작해보세요.")
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


@Preview
@Composable
fun pre(){
    LoginScreen()
}



