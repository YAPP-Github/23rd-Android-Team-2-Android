package com.moneymong.moneymong.feature.mymong.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.mymong.util.myMongRoundRectShadow

@Composable
internal fun MyMongSettingView(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "내 설정",
            color = Gray10,
            style = Body4
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .myMongRoundRectShadow()
                .background(color = White, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            SettingItem(
                iconRes = R.drawable.ic_paper,
                title = "서비스 이용약관",
                onNavigateClick = { /* todo */ }
            )
            SettingDivider()
            SettingItem(
                iconRes = R.drawable.ic_paper,
                title = "개인정보 처리 방침",
                onNavigateClick = { /* todo */ }
            )
            SettingDivider()
            SettingItem(
                iconRes = R.drawable.ic_logout,
                title = "로그아웃",
                onNavigateClick = { /* todo */ }
            )
            SettingDivider()
            SettingItem(
                iconRes = R.drawable.ic_delete,
                title = "회원탈퇴",
                onNavigateClick = { /* todo */ }
            )
            SettingDivider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_warning_outline),
                    tint = Gray07,
                    contentDescription = "version info icon"
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = "버전 정보",
                    color = Gray06,
                    style = Body4
                )
                Text(
                    text = "1.0.0",
                    color = Blue04,
                    style = Body4
                )
            }
        }
    }
}

@Composable
private fun SettingItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    title: String,
    onNavigateClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = iconRes),
            tint = Gray07,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = Gray06,
            style = Body4
        )
        Icon(
            modifier = Modifier.clickable { onNavigateClick() },
            painter = painterResource(id = R.drawable.ic_chevron_right),
            tint = Gray07,
            contentDescription = "navigate icon",
        )
    }
}

@Composable
private fun SettingDivider() {
    Divider(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .height(1.dp),
        color = Gray02
    )
}