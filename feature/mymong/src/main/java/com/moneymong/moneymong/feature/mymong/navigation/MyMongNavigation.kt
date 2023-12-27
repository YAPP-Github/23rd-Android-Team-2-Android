package com.moneymong.moneymong.feature.mymong.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.MyMongScreen

const val mymongRoute = "mymong_route"

fun NavGraphBuilder.mymongScreen(padding: PaddingValues) {
    composable(route = mymongRoute) {
        MyMongScreen(modifier = Modifier.padding(padding))
    }
}