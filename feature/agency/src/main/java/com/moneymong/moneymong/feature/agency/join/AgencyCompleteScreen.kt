package com.moneymong.moneymong.feature.agency.join

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.moneymong.moneymong.design_system.R
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.join.component.AgencyCompleteButtonView
import com.moneymong.moneymong.feature.agency.join.component.AgencyCompleteView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencyCompleteScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = MMHorizontalSpacing),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .background(White),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    text = "가입완료",
                    style = Heading1,
                    color = Black,
                    textAlign = TextAlign.Center
                )
                Icon(
                    painterResource(id = R.drawable.ic_close_default),
                    modifier = Modifier
                        .clickable { },
                    contentDescription = null
                )
            }
        },
        content = { innerPadding ->
            SignCompleteContent(modifier = Modifier.padding(innerPadding))
        }
    )
}


@Composable
fun SignCompleteContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        AgencyCompleteView()
        AgencyCompleteButtonView(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun CompletePreview() {
    AgencyCompleteScreen()
}