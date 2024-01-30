package com.moneymong.moneymong.feature.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignCompleteScreen(
    navigateToLedger : () -> Unit
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(MMHorizontalSpacing),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                    titleContentColor = Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                title = {
                    Text(
                        text = "가입완료",
                        textAlign = TextAlign.Center,
                        style = Heading1,
                        color = Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    )
                },
            )
        },
        content = { innerPadding ->
            SignCompleteContent(
                modifier = Modifier.padding(innerPadding),
                onChangeCompleteBtn = { onChangeCompleteBtn() }
            )
        }
    )
}


@Composable
fun SignCompleteContent(
    modifier: Modifier = Modifier,
    onChangeCompleteBtn : () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        SignCompleteView(modifier)
        SignCompleteButtonView(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onChangeCompleteBtn = onChangeCompleteBtn
        )
    }
}
