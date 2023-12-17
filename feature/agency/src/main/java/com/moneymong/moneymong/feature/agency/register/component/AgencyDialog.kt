package com.moneymong.moneymong.feature.agency.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencyOutDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onPositive: () -> Unit,
    onNegative: () -> Unit,
) {
    AlertDialog(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = White),
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier.padding(start = 22.dp, end = 22.dp, top = 24.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.ic_warning_filled),
                tint = Color.Unspecified,
                contentDescription = "Cancel Agency register warning icon"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "정말 나가시겠습니까?",
                style = Heading1,
                color = Gray10
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "입력하신 내용은 저장되지 않습니다.",
                style = Body4,
                color = Gray05
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MDSButton(
                    modifier = Modifier.weight(1f),
                    onClick = onNegative,
                    text = "취소",
                    type = MDSButtonType.SECONDARY
                )
                MDSButton(
                    modifier = Modifier.weight(1f),
                    onClick = onPositive,
                    text = "확인",
                    type = MDSButtonType.PRIMARY
                )
            }
        }
    }
}

