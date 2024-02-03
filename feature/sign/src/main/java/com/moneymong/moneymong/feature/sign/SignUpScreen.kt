package com.moneymong.moneymong.feature.sign

import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.error.ErrorDialog
import com.moneymong.moneymong.design_system.error.ErrorScreen
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.sign.sideeffect.SignUpSideEffect
import com.moneymong.moneymong.feature.sign.state.SignUpState
import com.moneymong.moneymong.feature.sign.view.SearchUnivView
import com.moneymong.moneymong.feature.sign.view.SignCompleteCheckedView
import com.moneymong.moneymong.feature.sign.view.SignUpButtonView
import com.moneymong.moneymong.feature.sign.view.SignUpGradeView
import com.moneymong.moneymong.feature.sign.view.SignUpTitleView
import com.moneymong.moneymong.feature.sign.viewmodel.SignUpViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SignUpScreen(
    navigateToSignComplete: () -> Unit,
    navigateUp: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value

    BackHandler {
        navigateUp()
    }

    if(state.visibleError){
        ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            message = state.errorMessage,
            onRetry = {
                viewModel.visibleErrorChanged(false)
            }
        )
    }
    else if(state.visiblePopUpError){
        ErrorDialog(
            message = state.popUpErrorMessage,
            onConfirm = {
                viewModel.visiblePopUpErrorChanged(false)
            }
        )
    }
    else{
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(MMHorizontalSpacing),
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .background(White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_left),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .background(White)
                            .noRippleClickable {
                                navigateUp()
                            },
                        tint = Gray07
                    )
                }
            },
            content = { innerPadding ->
                SignUpContent(
                    modifier = Modifier.padding(innerPadding),
                    navigateToSignComplete = navigateToSignComplete,
                    viewModel = viewModel,
                    state = state
                )
            }
        )
    }
}


@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    navigateToSignComplete: () -> Unit,
    viewModel: SignUpViewModel,
    state: SignUpState
) {

    LaunchedEffect(key1 = state.isUnivCreated) {
        Log.d("isUnivCreated", state.isUnivCreated.toString())
        if (state.isUnivCreated) {
            navigateToSignComplete()
        }
    }

    viewModel.collectSideEffect {
        when (it) {
            is SignUpSideEffect.UniversitiesApi -> {
                viewModel.searchUniv(it.univ)
            }

            is SignUpSideEffect.CreateUniversityApi -> {
                viewModel.createUniv(it.universityName, it.grade)
            }
        }
    }

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
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                subTitleState = state.subTitleState
            )

            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
            ) {
                if (!state.isSelected) {
                    SearchUnivView(
                        isFilled = state.isFilled,
                        isFilledChanged = { isFilled -> viewModel.isFilledChanged(isFilled) },
                        isListVisible = state.isListVisible,
                        isListVisibleChanged = { isListVisible ->
                            viewModel.isListVisibleChanged(
                                isListVisible
                            )
                        },
                        isItemSelectedChanged = { isItemSelected ->
                            viewModel.isItemSelectedChanged(
                                isItemSelected
                            )
                        },
                        isItemSelected = state.isItemSelected,
                        textValue = state.textValue,
                        universityResponse = state.universityResponse,
                        onClick = {
                            viewModel.isSelectedChanged(true)
                            viewModel.selectedUnivChanged(it)
                        },
                        onChange = { viewModel.textValueChanged(it) },
                        onSearchIconClicked = {
                            viewModel.eventEmit(SignUpSideEffect.UniversitiesApi(it))
                        },
                        value = state.textValue,
                        textInput = state.textInput,
                        textValueChanged = { isTextInput -> viewModel.textInputChanged(isTextInput)},
                        isButtonVisibleChanged = { isButtonVisible -> viewModel.isButtonVisibleChanged(isButtonVisible)}
                    )
                } else {
                    Column(modifier = Modifier.fillMaxSize()) {
                        SignCompleteCheckedView(
                            modifier = Modifier.fillMaxWidth(),
                            text = state.selectedUniv,
                            onChanged = {
                                viewModel.isSelectedChanged(false)
                            },
                            onSelectedGrade = { viewModel.selectedGradeChange(null) },
                            onItemSelectedChange = { viewModel.isItemSelectedChanged(it) },
                        )
                        SignUpGradeView(
                            modifier = Modifier.fillMaxWidth(),
                            selectedGrade = state.selectedGrade,
                            selectedGradeChange = {selectedGrade -> viewModel.selectedGradeChange(selectedGrade)},
                            onClick = { viewModel.isEnabledChanged(true) },
                            changeGradeInfor = { viewModel.gradeInforChanged(it) }
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            if(state.isButtonVisible){
                SignUpButtonView(
                    modifier = Modifier.fillMaxWidth(),
                    isEnabled = state.isEnabled,
                    visiblePopUpError = state.visiblePopUpError,
                    popUpErrorMessage = state.popUpErrorMessage,
                    visiblePopUpErrorChanged = { visiblePopUpError ->
                        viewModel.visiblePopUpErrorChanged(visiblePopUpError)
                    },
                    onCreateUniversity = {
                        viewModel.eventEmit(
                            SignUpSideEffect.CreateUniversityApi(
                                state.selectedUniv,
                                state.gradeInfor
                            )
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview(){
    SignUpScreen(
        navigateUp = {},
        navigateToSignComplete = {}
    )
}
