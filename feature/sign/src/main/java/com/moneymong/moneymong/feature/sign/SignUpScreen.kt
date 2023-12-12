package com.moneymong.moneymong.feature.sign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.MMTheme
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.sign.view.SearchUnivView
import com.moneymong.moneymong.feature.sign.view.SignCompleteCheckedView
import com.moneymong.moneymong.feature.sign.view.SignUpButtonView
import com.moneymong.moneymong.feature.sign.view.SignUpTitleView

class SignUpScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MMTheme {
                SignUpscreen()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpscreen() {
    Scaffold(
        modifier = Modifier
            .background(White)
            .padding(MMHorizontalSpacing)
            .background(White),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                    titleContentColor = Black,
                ),
                title = { Text(text = "")},
                modifier = Modifier
                    .background(White)
                    .width(24.dp)
                    .height(44.dp),
                navigationIcon = {
                    IconButton(
                        modifier = Modifier
                        .fillMaxSize()
                        .background(White),
                        onClick = { /* Handle navigation icon click */ },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_left),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp),
                            tint = Gray07
                        )
                    }
                },
            )
        },
        content = { innerPadding ->
            SignUpContent(modifier = Modifier.padding(innerPadding))
        }
    )
}


@Composable
fun SignUpContent (modifier : Modifier = Modifier ){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            SignUpTitleView(
                modifier = Modifier
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
                .fillMaxWidth()
            )

            Box(
                modifier= Modifier
                    .padding(0.dp, 40.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
            ){
                SearchUnivView()
                SignCompleteCheckedView(true, "")
            }


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            SignUpButtonView(modifier = Modifier.fillMaxWidth())
        }
    }
}



@Preview
@Composable
fun preview(){
    SignUpscreen()
}
