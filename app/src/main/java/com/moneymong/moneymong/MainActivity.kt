package com.moneymong.moneymong

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import com.moneymong.moneymong.design_system.theme.MMTheme
import com.moneymong.moneymong.feature.sign.LoginScreen
import com.moneymong.moneymong.feature.sign.SignUpScreen
import com.moneymong.moneymong.ocr.OCRScreen
import com.moneymong.moneymong.ui.MoneyMongApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MMTheme {
                MoneyMongApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}