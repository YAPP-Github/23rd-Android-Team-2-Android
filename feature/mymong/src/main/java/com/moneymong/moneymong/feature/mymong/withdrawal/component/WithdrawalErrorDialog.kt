package com.moneymong.moneymong.feature.mymong.withdrawal.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithdrawalErrorDialog(      // todo: change to ErrorDialog
    modifier: Modifier = Modifier,
    message: String,
    onConfirm: () -> Unit
) {
    AlertDialog(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = White),
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier.padding(start = 22.dp, end = 22.dp, top = 24.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.ic_warning_filled),
                tint = Color.Unspecified,
                contentDescription = "warning"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = Heading1,
                color = Gray10
            )
            Spacer(modifier = Modifier.height(16.dp))
            MDSButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onConfirm,
                text = "확인",
                type = MDSButtonType.PRIMARY,
                size = MDSButtonSize.LARGE,
            )
        }
    }
}
