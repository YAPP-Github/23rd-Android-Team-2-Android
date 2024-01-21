package com.moneymong.moneymong.feature.agency.join

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.join.component.AgencyCompleteButtonView
import com.moneymong.moneymong.feature.agency.join.component.AgencyCompleteView
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun AgencyCompleteScreen(
    modifier : Modifier = Modifier,
    navController: NavHostController,
    navigateToLedger : () -> Unit,
    navigateToJoin : () -> Unit,
    navigateUp: () -> Unit,
    viewModel: AgencyCompleteViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value

    // 뒤로 가기 버튼 핸들러
    BackHandler(enabled = true) {
        navigateToJoin()
    }
    Scaffold(
        modifier = modifier
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
                        .clickable {
                            navigateToJoin()
                        },
                    contentDescription = null
                )
            }
        },
        content = { innerPadding ->
            SignCompleteContent(
                modifier = Modifier.padding(innerPadding),
                navigateToLedger = navigateToLedger,
                viewModel = viewModel,
                state = state

            )
        }
    )
}


@Composable
fun SignCompleteContent(
    modifier: Modifier = Modifier,
    navigateToLedger: () -> Unit,
    viewModel : AgencyCompleteViewModel,
    state : AgencyCompleteState
) {

    LaunchedEffect(key1 = state.isBtnClicked){
        if(state.isBtnClicked == true){
            navigateToLedger()
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        AgencyCompleteView()
        AgencyCompleteButtonView(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            viewModel = viewModel,
        )
    }
}


