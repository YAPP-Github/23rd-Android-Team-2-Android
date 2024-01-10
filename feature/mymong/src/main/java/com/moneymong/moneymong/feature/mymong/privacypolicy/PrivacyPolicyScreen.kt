package com.moneymong.moneymong.feature.mymong.privacypolicy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.mymong.component.MyMongInnerTopBar
import com.moneymong.moneymong.feature.mymong.component.MyMongWebView

@Composable
fun PrivacyPolicyScreen(
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
                title = "개인정보 처리 방침",
                onBackClick = onBack
            )
        }
    ) {
        MyMongWebView(
            modifier = Modifier.padding(it),
            url = "https://luck-walk-147.notion.site/7f4338eda8564c1ca4177caecf5aedc8"
        )
    }
}

@Preview
@Composable
fun PreviewPrivacyPolicyScreen() {
    PrivacyPolicyScreen()
}