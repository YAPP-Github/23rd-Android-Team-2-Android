package com.moneymong.moneymong.feature.mymong.withdrawal.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1

@Composable
internal fun MyMongWithdrawalTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}    // todo
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .size(24.dp)
                .clickable { onBackClick() },
            painter = painterResource(id = R.drawable.ic_chevron_left),
            contentDescription = "back button",
            tint = Gray07
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "회원탈퇴",
            color = Gray10,
            style = Heading1
        )
        Spacer(modifier = Modifier.size(24.dp))
    }
}