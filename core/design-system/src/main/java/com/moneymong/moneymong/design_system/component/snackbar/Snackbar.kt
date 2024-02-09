package com.moneymong.moneymong.design_system.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Red03
import com.moneymong.moneymong.design_system.theme.White

@Composable
internal fun MDSSnackbar(
    snackbarData: SnackbarData
) {
    val actionLabel = snackbarData.visuals.actionLabel
    val actionComposable: (@Composable () -> Unit)? =
        if (actionLabel != null) {
            @Composable {
                Box(
                    modifier = Modifier
                        .padding(start = 4.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                        .background(color = Color.Transparent)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { snackbarData.performAction() }
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        text = actionLabel,
                        color = Red03,
                        style = Body3
                    )
                }
            }
        } else {
            null
        }

    val dismissActionComposable: (@Composable () -> Unit)? =
        if (snackbarData.visuals.withDismissAction) {
            @Composable {
                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = { snackbarData.dismiss() },
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_close_default),
                            contentDescription = "Close snackbar",
                            tint = White,
                        )
                    }
                )
            }
        } else {
            null
        }

    MDSSnackbar(
        message = snackbarData.visuals.message,
        actionComposable = actionComposable,
        dismissActionComposable = dismissActionComposable
    )
}


@Composable
private fun MDSSnackbar(
    message: String,
    actionComposable: (@Composable () -> Unit)? = null,
    dismissActionComposable: (@Composable () -> Unit)? = null
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Gray07,
        shape = RoundedCornerShape(size = 8.dp),
        shadowElevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, top = 14.dp, bottom = 14.dp),
                text = message,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = White,
                style = Body3
            )
            actionComposable?.invoke()
            dismissActionComposable?.invoke()
        }
    }
}