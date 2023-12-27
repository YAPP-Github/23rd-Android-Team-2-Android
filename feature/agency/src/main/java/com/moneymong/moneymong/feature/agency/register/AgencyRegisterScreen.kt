package com.moneymong.moneymong.feature.agency.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.register.component.AgencyOutDialog
import com.moneymong.moneymong.feature.agency.register.view.AgencyResisterContentView
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AgencyRegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: AgencyRegisterViewModel = hiltViewModel(),
    navigateToComplete: () -> Unit,
    navigateUp: () -> Unit
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            is AgencyRegisterSideEffect.NavigateToComplete -> {
                navigateToComplete()
            }

            is AgencyRegisterSideEffect.NavigateUp -> {
                navigateUp()
            }
        }
    }

    if (state.visibleOutDialog) {
        AgencyOutDialog(
            onDismissRequest = { viewModel.changeVisibleDialog(false) },
            onPositive = { viewModel.onDialogPositiveButtonClicked() },
            onNegative = { viewModel.changeVisibleDialog(false) }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = MMHorizontalSpacing)
            .background(color = White)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = 10.dp)
                .size(24.dp)
                .clickable(onClick = { viewModel.changeVisibleDialog(true) }),
            painter = painterResource(id = R.drawable.ic_close_default),
            tint = Gray07,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        AgencyResisterContentView(
            modifier = Modifier.weight(1f),
            agencyType = state.agencyType,
            onAgencyTypeChange = viewModel::onAgencyTypeChanged,
            agencyName = state.agencyName,
            onAgencyNameChange = viewModel::onAgencyNameChanged,
            changeNameTextFieldIsError = viewModel::changeNameTextFieldIsError,
        )
        val canRegister = state.agencyName.text.isNotEmpty() && state.nameTextFieldIsError.not()
        MDSButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp),
            onClick = viewModel::onRegisterButtonClicked,
            text = "등록하기",
            enabled = canRegister
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AgencyRegisterScreenPreview() {
    AgencyRegisterScreen(
        navigateToComplete = {},
        navigateUp = {}
    )
}