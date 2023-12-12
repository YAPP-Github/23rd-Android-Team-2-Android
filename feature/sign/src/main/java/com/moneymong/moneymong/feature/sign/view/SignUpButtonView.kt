package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType

@Composable
fun SignUpButtonView(modifier: Modifier = Modifier , isEnabled : Boolean) {


    Column(
        modifier = modifier
    ) {
        MDSButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

            },
            text = "가입하기",
            type = MDSButtonType.PRIMARY,
            size = MDSButtonSize.LARGE,
            enabled = isEnabled
        )
        Spacer(modifier = Modifier.height(8.dp))

    }
}