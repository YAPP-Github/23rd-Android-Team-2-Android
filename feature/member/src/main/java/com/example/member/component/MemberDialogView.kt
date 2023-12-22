package com.example.member.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun MemberDialogView(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth(),
        icon = {
            Image(
                painterResource(
                    id = com.moneymong.moneymong.design_system.R.drawable.ic_warning_filled
                ),
                modifier = Modifier.size(60.dp),
                contentDescription = null,

            )
        },
        title = {
            Text(
                text = "정말 내보내시겠습니까?",
                style = Heading1,
                color = Gray10
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                MDSButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    onClick = {
                        onConfirmation()
                    },
                    text = "취소",
                    type = MDSButtonType.SECONDARY,
                    size = MDSButtonSize.LARGE
                )
                Spacer(modifier = Modifier.width(12.dp))
                MDSButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    onClick = {
                        onDismissRequest()
                    },
                    text = "확인",
                    type = MDSButtonType.PRIMARY,
                    size = MDSButtonSize.LARGE
                )
            }
        },
        containerColor = White
    )
}

@Preview
@Composable
fun PreDialog() {
    MemberDialogView(onDismissRequest = { }, onConfirmation = { })

}