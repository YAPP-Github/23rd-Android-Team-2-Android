package com.moneymong.moneymong.feature.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.sign.view.SignCompleteButtonView
import com.moneymong.moneymong.feature.sign.view.SignCompleteView

@Composable
fun SignCompleteScreen(
    modifier: Modifier = Modifier,
    navigateToLedger: () -> Unit
) {

    val isCompleteBtnClicked = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isCompleteBtnClicked.value) {
        if (isCompleteBtnClicked.value) {
            navigateToLedger()
        }
    }

    fun onChangeCompleteBtn() {
        isCompleteBtnClicked.value = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = MMHorizontalSpacing)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            text = "가입완료",
            textAlign = TextAlign.Center,
            style = Heading1,
            color = Black,
        )
        SignCompleteView(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        )
        SignCompleteButtonView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp),
            onChangeCompleteBtn = ::onChangeCompleteBtn
        )
    }
}