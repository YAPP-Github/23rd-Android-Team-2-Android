package com.moneymong.moneymong.design_system.component.textfield.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Caption

@Composable
internal fun MDSTextFieldContainer(
    modifier: Modifier = Modifier,
    state: MDSTextFieldState,
    title: String,
    count: Int,
    placeholder: String,
    icon: MDSTextFieldIcons?,
    onIconClick: (() -> Unit),
    maxCount: Int?,
    helperText: String?,
    innerTextField: @Composable () -> Unit
) {

    Column(modifier = modifier) {
        MDSTextFieldContainerTop(
            title = title,
            state = state
        )
        Spacer(modifier = Modifier.height(8.dp))
        MDSTextFieldContainerCenter(
            placeholder = placeholder,
            state = state,
            icon = icon,
            onIconClick = onIconClick,
            innerTextField = innerTextField
        )
        Spacer(modifier = Modifier.height(2.dp))
        MDSTextFieldContainerBottom(
            count = count,
            state = state,
            maxCount = maxCount,
            helperText = helperText
        )
    }
}

@Composable
private fun MDSTextFieldContainerTop(
    title: String,
    state: MDSTextFieldState
) {
    Text(
        text = title,
        color = state.titleColor,
        style = Body2
    )
}

@Composable
private fun MDSTextFieldContainerCenter(
    placeholder: String,
    state: MDSTextFieldState,
    icon: MDSTextFieldIcons?,
    onIconClick: (() -> Unit),
    innerTextField: @Composable () -> Unit
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.weight(1f)) {
            innerTextField()
            if (state == MDSTextFieldState.Default) {
                Text(
                    text = placeholder,
                    color = state.textColor,
                    style = Body3
                )
            }
        }
        if (icon != null && icon.visibleByState(state)) {
            IconButton(
                modifier = Modifier.size(20.dp),
                onClick = onIconClick
            ) {
                Icon(
                    painter = painterResource(id = icon.resourceId),
                    contentDescription = null,
                    tint = icon.color(state)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = state.underLineColor
    )
}

@Composable
private fun MDSTextFieldContainerBottom(
    count: Int,
    state: MDSTextFieldState,
    maxCount: Int? = null,
    helperText: String? = null
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        if (helperText != null && state == MDSTextFieldState.Error) {
            Text(
                text = helperText,
                color = MDSTextFieldState.Error.errorColor,
                textAlign = TextAlign.Start,
                style = Caption
            )
        } else {
            Text(
                text = "",
                textAlign = TextAlign.Start,
                style = Caption
            )
        }
        if (state.countVisible && maxCount != null) {
            val countColor = if (state == MDSTextFieldState.Error) {
                MDSTextFieldState.Error.countColor
            } else {
                MDSTextFieldState.Active.countColor
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "$count/$maxCount",
                color = countColor,
                textAlign = TextAlign.End,
                style = Caption
            )
        }
    }
}