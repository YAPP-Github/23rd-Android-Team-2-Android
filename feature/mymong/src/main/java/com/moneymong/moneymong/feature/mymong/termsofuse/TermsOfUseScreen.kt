package com.moneymong.moneymong.feature.mymong.termsofuse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.mymong.R
import com.moneymong.moneymong.feature.mymong.component.MyMongInnerTopBar
import com.moneymong.moneymong.feature.mymong.component.markdown.MyMongMarkdownView

@Composable
fun TermsOfUseScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = White)
            .padding(horizontal = MMHorizontalSpacing)
    ) {
        MyMongInnerTopBar(
            title = "서비스 이용약관",
            onBackClick = navigateUp
        )
        MyMongMarkdownView(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            markdownText = stringResource(id = R.string.terms_of_use_content),
        )
    }
}