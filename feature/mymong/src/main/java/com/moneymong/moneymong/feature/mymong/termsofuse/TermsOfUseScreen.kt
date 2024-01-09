package com.moneymong.moneymong.feature.mymong.termsofuse

import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.mymong.component.MyMongInnerTopBar

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
        ContentView(
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
private fun ContentView(
    modifier: Modifier = Modifier,
    url: String = "https://luck-walk-147.notion.site/8a382c0e511448838d2d350e16df3a95"
) {
    AndroidView(
        modifier = modifier,
        factory = {
            WebView(it).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}


@Preview
@Composable
fun PreviewTermsOfUseScreen() {
    TermsOfUseScreen()
}