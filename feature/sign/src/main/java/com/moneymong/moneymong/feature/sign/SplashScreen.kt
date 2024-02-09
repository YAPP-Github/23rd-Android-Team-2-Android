package com.moneymong.moneymong.feature.sign

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.feature.sign.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToLedger: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val state = viewModel.collectAsState().value

    LaunchedEffect(key1 = state.isTokenValid) {
        if (state.isTokenValid == true ) {
            navigateToLedger()
        } else if(state.isTokenValid == false ) {
            navigateToLogin()
        }
    }

    val scale = animateFloatAsState(
        targetValue = if (state.startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 350), label = ""
    )

    LaunchedEffect(key1 = Unit) {
        delay(1)
        viewModel.startAnimationChanged(true)
        delay(300)
        viewModel.checkTokenValidity()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Blue04)
            .scale(scale.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.img_profile),
            contentDescription = null
        )

    }
}
