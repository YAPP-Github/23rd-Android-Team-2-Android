package com.moneymong.moneymong.ui

import androidx.compose.runtime.Composable
import com.moneymong.moneymong.home.HomeScreen

@Composable
fun MoneyMongApp(
    expired: Boolean,
    onChangeExpired: (Boolean) -> Unit
) {
    HomeScreen(expired = expired, onChangeExpired = onChangeExpired)
}