package com.moneymong.moneymong.design_system.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    message: String,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = MMHorizontalSpacing)
                .clip(RoundedCornerShape(20.dp))
                .background(color = White)
                .padding(start = 22.dp, end = 22.dp, top = 24.dp, bottom = 20.dp),
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

@Preview(
    showBackground = true,
    device = "spec:shape=Normal,width=220,height=640, unit=dp, dpi= 480"
)
@Composable
fun ErrorDialogPreview() {
    var visibleDialog by remember { mutableStateOf(true) }

    if (visibleDialog) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ErrorDialog(
                message = "네트워크 연결을 확인해주세요",
                onConfirm = { visibleDialog = false }
            )
        }
    }
}