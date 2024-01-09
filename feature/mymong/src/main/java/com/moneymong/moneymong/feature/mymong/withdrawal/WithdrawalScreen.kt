package com.moneymong.moneymong.feature.mymong.withdrawal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.Red03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.mymong.component.MyMongInnerTopBar
import com.moneymong.moneymong.feature.mymong.withdrawal.component.MyMongCheckBox

@Composable
fun MyMongWithdrawalScreen(
    modifier: Modifier = Modifier
) {
    var isAgreed by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
            .padding(horizontal = MMHorizontalSpacing),
    ) {
        MyMongInnerTopBar(title = "회원탈퇴")
        Spacer(modifier = Modifier.height(20.dp))
        ContentView(
            isChecked = isAgreed,
            onCheckedChange = { isAgreed = it }
        )
        Spacer(modifier = Modifier.weight(1f))
        MDSButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp),
            text = "탈퇴하기",
            onClick = { /* todo */ },
            enabled = isAgreed
        )
    }
}

@Composable
private fun ContentView(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Gray03, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            text = "탈퇴하시겠어요?",
            color = Red03,
            style = Heading1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "본인이 소속된 기관의 장부 기록 내역은 삭제되지 않습니다. 그 외의 이용내역은 삭제되며, 복원이 불가능합니다.",
            color = Gray07,
            style = Body4
        )
        Divider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .height(1.dp),
            color = Gray02
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            MyMongCheckBox(
                checked = isChecked,
                onCheckedChange = onCheckedChange
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "해당 내용에 동의하시겠습니까?",
                color = Gray07,
                style = Body3
            )
        }
    }
}