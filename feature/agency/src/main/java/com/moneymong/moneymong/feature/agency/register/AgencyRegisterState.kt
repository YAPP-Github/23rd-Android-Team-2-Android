package com.moneymong.moneymong.feature.agency.register

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.feature.agency.search.AgencyType

data class AgencyRegisterState(
    val agencyName: TextFieldValue = TextFieldValue(),
    val agencyType: AgencyType = AgencyType.CLUB,
    val errorMessage: String = "",
    val nameTextFieldIsError: Boolean = false,
    val visibleOutDialog: Boolean = false,
    val visibleErrorDialog: Boolean = false,
) : State