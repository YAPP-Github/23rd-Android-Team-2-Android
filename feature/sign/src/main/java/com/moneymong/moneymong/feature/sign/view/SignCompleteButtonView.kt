package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType

@Composable
fun SignCompleteButtonView(
    modifier: Modifier = Modifier,
    onChangeCompleteBtn: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        MDSButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onChangeCompleteBtn()
            },
            text = "홈으로 이동하기",
            type = MDSButtonType.PRIMARY,
            size = MDSButtonSize.LARGE,
            enabled = true
        )
    }
}

