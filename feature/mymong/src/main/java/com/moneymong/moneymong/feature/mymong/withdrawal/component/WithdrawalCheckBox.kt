package com.moneymong.moneymong.feature.mymong.withdrawal.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun WithdrawalCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
) {
    if (checked) {
        CheckedCheckBox(modifier = modifier)
    } else {
        UncheckedCheckBox(modifier = modifier)
    }
}

@Composable
private fun CheckedCheckBox(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(18.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = Blue04)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_check_checkbox),
            tint = White,
            contentDescription = "check icon"
        )
    }
}

@Composable
private fun UncheckedCheckBox(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(18.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = Color.Transparent)
            .border(width = 2.dp, color = Gray03, shape = RoundedCornerShape(4.dp))
    )
}
