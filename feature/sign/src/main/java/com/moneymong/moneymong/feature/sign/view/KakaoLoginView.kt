package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R

@Composable
fun KakaoLoginView(
    modifier: Modifier = Modifier,
    onLoginButtonClicked: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onLoginButtonClicked()
                },
            painter = painterResource(id = R.drawable.img_kakao_login),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
    }
}
