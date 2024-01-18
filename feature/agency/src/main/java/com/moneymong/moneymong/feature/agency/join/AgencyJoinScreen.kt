package com.moneymong.moneymong.feature.agency.join

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.snackbar.MDSSnackbarHost
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading3
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.join.component.AgencyInviteCodeView
import com.moneymong.moneymong.feature.agency.join.navigation.agencyCompleteRoute
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun AgencyJoinScreen(
    viewModel: AgencyJoinViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.collectAsState().value
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = MMHorizontalSpacing),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .height(44.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { },
                    painter = painterResource(id = R.drawable.ic_close_default),
                    contentDescription = null,
                    tint = Black
                )
            }
        },
        content = { innerPadding ->
            JoinContent(
                modifier = Modifier.padding(innerPadding),
                state = state,
                viewModel = viewModel,
                navController = navController
            )
        }
    )
}

@Composable
private fun JoinContent(
    modifier: Modifier = Modifier,
    state: AgencyJoinState,
    viewModel: AgencyJoinViewModel,
    navController: NavHostController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequesters = remember { List(6) { FocusRequester() } }

    LaunchedEffect(key1 = state.isError) {
        Log.d("launch", state.isError.toString())
        if (state.isError) {
            val result = snackbarHostState.showSnackbar(
                message = "잘못된 초대코드입니다.",
                actionLabel = "다시입력"
            )

            if (result == SnackbarResult.ActionPerformed) {
                focusRequesters[0].requestFocus()
                viewModel.onIsErrorChanged(false)
                viewModel.resetNumbers()
            }
        }
    }

    LaunchedEffect(key1 = state.codeAccess) {
        if (state.codeAccess) {
            navController.navigate(agencyCompleteRoute) {
                //TODO - popupto
            }
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(top = 8.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Yapp에서 받은\n초대코드를 입력해주세요",
            color = Gray10,
            style = Heading3
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 151.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            AgencyInviteCodeView(
                viewModel = viewModel,
                state = state,
                focusRequesters = focusRequesters,
                onIsErrorChanged = { isError -> viewModel.onIsErrorChanged(isError) },
                onIsNumbersChanged = { index, value -> viewModel.onIsNumberChanged(index, value) },
            )

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            MDSSnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(BottomCenter)
            )
        }

    }
}



