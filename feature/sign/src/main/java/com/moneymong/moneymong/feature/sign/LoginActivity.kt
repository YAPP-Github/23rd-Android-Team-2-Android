package com.moneymong.moneymong.feature.sign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Heading3
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.MMTheme
import com.moneymong.moneymong.design_system.theme.White
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MMTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    Scaffold(
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            LoginContent(modifier)
        }
    )
}


//@Composable
//fun LoginContent(modifier: Modifier = Modifier) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(White)
////                .align(Alignment.TopStart)
//        , horizontalAlignment = Alignment.Start
//    ) {
//        TitleView("교내 회계관리를 편리하게\n시작해보세요.")
//        Box(
//            modifier = Modifier
////                .align(Alignment.BottomCenter)
//                .paddingFromBaseline(bottom = 76.dp)
//        ) {
//            KakaoLoginView(modifier = Modifier.fillMaxWidth())
//        }
//    }
//
//}

@Composable
private fun LoginContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = MMHorizontalSpacing, end = MMHorizontalSpacing)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 64.dp)
        ) {
            TitleView("교내 회계관리를 편리하게\n시작해보세요.")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .paddingFromBaseline(bottom = 76.dp)
        ) {
            KakaoLoginView(modifier = Modifier.fillMaxWidth())
        }
    }
}


@Composable
fun TitleView(text: String) {
    Column(
        horizontalAlignment = Alignment.Start
    ){
        Spacer(modifier = Modifier.height(44.dp))
        Text(
            text = text,
            style = Heading3,
        )
    }
}

@Composable
fun KakaoLoginView(modifier: Modifier = Modifier) {
    IconButton(
        onClick = {
            // 클릭 시 수행할 동작
        },
        modifier = modifier
            .background(White)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = com.moneymong.moneymong.design_system.R.drawable.img_kakao_login),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleViewPreview() {
    TitleView("교내 회계관리를 편리하게\n시작해보세요.")
}

@Preview(showBackground = true)
@Composable
fun KakaoLoginViewPreview() {
    KakaoLoginView(modifier = Modifier
        .fillMaxWidth())
}

