package com.moneymong.moneymong.feature.agency.join.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType

@Composable
fun AgencyCompleteButtonView (modifier: Modifier = Modifier){

    Column(
        modifier = modifier
    ) {
        MDSButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            },
            text = "확인하러 가기",
            type = MDSButtonType.PRIMARY,
            size = MDSButtonSize.LARGE,
            enabled = true
        )
        Spacer(modifier = Modifier.height(28.dp))

    }
}

@Preview
@Composable
fun CompleteButtonPreview(){
    AgencyCompleteButtonView(modifier = Modifier.fillMaxWidth())
}