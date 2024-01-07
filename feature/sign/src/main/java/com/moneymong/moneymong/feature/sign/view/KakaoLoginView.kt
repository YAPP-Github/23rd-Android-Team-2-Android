package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.feature.sign.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun KakaoLoginView(modifier: Modifier = Modifier) {

    val viewModel : LoginViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        viewModel.onLoginButtonClicked()
                    }
                },
            painter = painterResource(id = R.drawable.img_kakao_login),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Spacer(modifier = Modifier.height(28.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun KakaoLoginViewPreview() {
    KakaoLoginView(modifier = Modifier
        .fillMaxWidth())
}
