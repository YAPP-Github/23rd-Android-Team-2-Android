package com.moneymong.moneymong.feature.sign.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.domain.entity.signup.University


@Composable
fun UnivItem(
    isItemSelected: Boolean,
    isItemSelectedChanged: (Boolean) -> Unit,
    univs: University,
    onClick: (String) -> Unit,
    isButtonVisibleChanged : (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .background(White)
            .fillMaxWidth()
            .noRippleClickable {
                isItemSelectedChanged(!isItemSelected)
                onClick(univs.schoolName)
                isButtonVisibleChanged(true)
            }
            .padding(bottom = 20.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.img_university),
            modifier = Modifier.size(22.dp),
            contentDescription = null
        )
        Text(
            text = univs.schoolName,
            color = if (isItemSelected) Blue04 else Black,
            style = Body4,
            modifier = Modifier
                .weight(1f)
                .height(22.dp)
                .padding(start = 10.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = if (isItemSelected) Blue04 else Gray03
        )
    }
}