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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.sign.University
import com.moneymong.moneymong.feature.sign.view.SignCompleteCheckedView


@Composable
fun UnivItem(univs: University, onClick : (String) -> Unit) {
    var isSelected by remember{ mutableStateOf(false) }

    Row(
        modifier = Modifier
            .background(White)
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 20.dp)
            .background(White)
            .clickable {
                onClick(univs.univ)
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_university),
            modifier = Modifier.size(22.dp),
            contentDescription = null
        )
        Text(
            text = univs.univ,
            color = if(isSelected) Blue04 else Black,
            style = Body4,
            modifier = Modifier
                .weight(1f)
                .height(22.dp)
                .padding(10.dp, 0.dp, 0.dp, 0.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = if(isSelected) Blue04 else Gray03
        )
    }
}