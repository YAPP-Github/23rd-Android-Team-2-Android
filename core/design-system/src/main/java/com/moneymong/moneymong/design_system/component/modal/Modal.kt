package com.moneymong.moneymong.design_system.component.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing

@Composable
fun MDSModal(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    description: String,
    negativeBtnText: String,
    positiveBtnText: String,
    onClickNegative: () -> Unit,
    onClickPositive: () -> Unit,
    onDismissRequest: () -> Unit = onClickNegative
) {
    val horizontalPadding = 22.dp
    val buttonWidth = 132.dp
    val buttonSpacing = 12.dp

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = MMHorizontalSpacing)
                .widthIn(max = horizontalPadding * 2 + buttonWidth * 2 + buttonSpacing)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Icon(
                modifier = Modifier.size(52.dp),
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = Heading1,
                color = Gray10
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                textAlign = TextAlign.Center,
                style = Body4,
                color = Gray05
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MDSButton(
                    modifier = Modifier
                        .weight(weight = 1f, fill = false)
                        .widthIn(min = buttonWidth),
                    text = negativeBtnText,
                    size = MDSButtonSize.LARGE,
                    type = MDSButtonType.NEGATIVE,
                    onClick = onClickNegative
                )
                Spacer(modifier = Modifier.width(12.dp))
                MDSButton(
                    modifier = Modifier
                        .weight(weight = 1f, fill = false)
                        .widthIn(min = buttonWidth),
                    text = positiveBtnText,
                    size = MDSButtonSize.LARGE,
                    type = MDSButtonType.PRIMARY,
                    onClick = onClickPositive
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:shape=Normal,width=240,height=640, unit=dp, dpi= 480"
)
@Composable
fun MDSModalPreview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MDSModal(
            icon = R.drawable.ic_camera_modal,
            title = "ddddddddddddddddddddddddddddddddddddddddddddddddddd?",
            description = "영수증 스캔을 위해 필요합니다",
            negativeBtnText = "허용 안함",
            positiveBtnText = "허용",
            onClickNegative = {},
            onClickPositive = {}
        )
    }
}