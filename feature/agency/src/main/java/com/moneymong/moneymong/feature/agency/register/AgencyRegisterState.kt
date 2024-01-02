package com.moneymong.moneymong.feature.agency.register

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.feature.agency.AgencyType

data class AgencyRegisterState(
    val agencyName: TextFieldValue = TextFieldValue(),
    val agencyType: AgencyType = AgencyType.CLUB,
    val nameTextFieldIsError: Boolean = false,
    val visibleOutDialog: Boolean = false,
) : State