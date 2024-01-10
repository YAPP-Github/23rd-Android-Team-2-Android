package com.moneymong.moneymong.feature.mymong.termsofuse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.mymong.component.MyMongInnerTopBar
import com.moneymong.moneymong.feature.mymong.component.MyMongWebView

@Composable
fun TermsOfUseScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MyMongInnerTopBar(
                modifier = Modifier
                    .background(color = White)
                    .padding(horizontal = MMHorizontalSpacing),
                title = "서비스 이용약관",
                onBackClick = onBack
            )
        }
    ) {
        MyMongWebView(
            modifier = Modifier.padding(it),
            url = "https://luck-walk-147.notion.site/8a382c0e511448838d2d350e16df3a95"
        )
    }
}