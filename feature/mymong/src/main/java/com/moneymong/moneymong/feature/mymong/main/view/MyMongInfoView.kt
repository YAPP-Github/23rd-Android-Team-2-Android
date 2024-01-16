package com.moneymong.moneymong.feature.mymong.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Gray08
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.mymong.main.util.myMongRoundRectShadow

@Composable
internal fun MyMongInfoView(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Profile()
        Spacer(modifier = Modifier.height(20.dp))
        InfoWithSchoolAndBirth()
    }
}

@Composable
private fun Profile(
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(6.dp))
        Image(
            modifier = modifier
                .size(36.dp)
                .drawBehind {
                    drawCircle(
                        color = Blue04,
                        radius = 48.dp.toPx() / 2f
                    )
                },
            painter = painterResource(id = R.drawable.img_profile),
            contentDescription = "profile Image"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "김마이몽",
                color = Gray10,
                style = Heading1
            )
            Text(
                text = "mymong@moneymong.com",
                color = Gray08,
                style = Body2
            )
        }
    }
}


@Composable
fun InfoWithSchoolAndBirth() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .myMongRoundRectShadow()
            .background(color = White, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 20.dp, horizontal = 16.dp)
    ) {
        InfoWithSchool()
    }
}

@Composable
fun InfoWithSchool() {
    Column {
        Text(
            text = "학교정보",
            color = Gray07,
            style = Body3
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.img_university),
                contentDescription = "school icon"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "머니몽학교 1학년",
                color = Gray08,
                style = Body4
            )
        }
    }
}