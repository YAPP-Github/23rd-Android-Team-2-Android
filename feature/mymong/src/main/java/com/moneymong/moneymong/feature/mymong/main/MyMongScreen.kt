package com.moneymong.moneymong.feature.mymong.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.feature.mymong.main.component.MyMongTopBar
import com.moneymong.moneymong.feature.mymong.main.view.MyMongInfoView
import com.moneymong.moneymong.feature.mymong.main.view.MyMongSettingView

@Composable
fun MyMongScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Gray01)
            .padding(horizontal = MMHorizontalSpacing),
    ) {
        MyMongTopBar(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(12.dp))
        MyMongInfoView()
        Spacer(modifier = Modifier.height(24.dp))
        MyMongSettingView()
    }
}
