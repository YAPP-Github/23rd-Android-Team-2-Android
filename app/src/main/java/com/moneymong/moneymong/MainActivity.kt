package com.moneymong.moneymong

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import com.moneymong.moneymong.design_system.theme.MMTheme
import com.moneymong.moneymong.domain.repository.TokenRepository
import com.moneymong.moneymong.domain.repository.user.UserRepository
import com.moneymong.moneymong.feature.sign.LoginScreen
import com.moneymong.moneymong.feature.sign.SignUpScreen
import com.moneymong.moneymong.ocr.OCRScreen
import com.moneymong.moneymong.ui.MoneyMongApp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tokenRepository: TokenRepository
    private val expired = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MMTheme {
                MoneyMongApp(expired.value) {
                    expired.value = false
                }
            }
        }

        CoroutineScope(Dispatchers.Default).launch {
            tokenRepository.tokenUpdateFailed.collect {
                expired.value = it
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