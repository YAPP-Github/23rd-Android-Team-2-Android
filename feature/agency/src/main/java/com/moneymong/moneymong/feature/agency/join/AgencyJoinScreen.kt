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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.snackbar.MDSSnackbarHost
import com.moneymong.moneymong.design_system.error.ErrorDialog
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading3
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.join.component.AgencyInviteCodeView
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun AgencyJoinScreen(
    modifier: Modifier = Modifier,
    viewModel: AgencyJoinViewModel = hiltViewModel(),
    navigateToComplete: () -> Unit,
    navigateUp: () -> Unit,
    agencyId: Long
) {
    val state = viewModel.collectAsState().value

    Scaffold(
        modifier = modifier
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
                        .noRippleClickable {
                            navigateUp()
                        },
                    painter = painterResource(id = R.drawable.ic_close_default),
                    contentDescription = null,
                    tint = Black
                )
            }
        },
        content = { innerPadding ->
            JoinContent(
                modifier = Modifier.padding(innerPadding),
                agencyId = agencyId,
                state = state,
                viewModel = viewModel,
                navigateToComplete = navigateToComplete,
                navigateUp = navigateUp
            )
        }
    )
}

@Composable
private fun JoinContent(
    modifier: Modifier = Modifier,
    agencyId: Long,
    state: AgencyJoinState,
    viewModel: AgencyJoinViewModel,
    navigateToComplete: () -> Unit,
    navigateUp: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequesters = remember { List(6) { FocusRequester() } }

    if (state.visiblePopUpError) {
        ErrorDialog(
            message = state.errorPopUpMessage,
            onConfirm = {
                viewModel.visiblePopUpErrorChanged(false)
                viewModel.onIsErrorChanged(false)
                viewModel.resetNumbers()
                navigateUp()
            }
        )
    }

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
            navigateToComplete()
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
                agencyId = agencyId,
                focusRequesters = focusRequesters,
                isError = state.isError,
                numbers = state.numbers,
                agencyCodeNumbers = { agencyId -> viewModel.agencyCodeNumbers(agencyId) },
                onIsErrorChanged = { isError -> viewModel.onIsErrorChanged(isError) },
                onIsNumbersChanged = { index, value -> viewModel.onIsNumbersChanged(index, value) },
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            MDSSnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(BottomCenter)
                    .padding(bottom = 20.dp)
            )
        }

    }
}



