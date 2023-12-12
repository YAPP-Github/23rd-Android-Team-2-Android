package com.moneymong.moneymong.design_system.component.infor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Body2

@Composable
fun MDSInfor(
    modifier: Modifier = Modifier,
    message: String,
    type: MDSInforType = MDSInforType.PRIMARY,
    iconVisible: Boolean = false,
) {
    Row(
        modifier = modifier
            .background(
                color = type.backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (iconVisible) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_infor),
                contentDescription = "infor icon",
                tint = type.contentColor
            )
        }
        Text(
            text = message,
            color = type.contentColor,
            style = Body2
        )
    }
}