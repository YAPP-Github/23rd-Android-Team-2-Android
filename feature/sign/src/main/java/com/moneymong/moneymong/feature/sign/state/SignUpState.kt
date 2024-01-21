package com.moneymong.moneymong.feature.sign.state

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.domain.entity.signup.UniversitiesEntity
import com.moneymong.moneymong.domain.entity.signup.UniversityX
import com.moneymong.moneymong.feature.sign.util.Grade

data class SignUpState(
    //screen
    val isSelected: Boolean = false,
    val selectedUniv: String = "",
    val textValue: TextFieldValue = TextFieldValue(),
    val isEnabled: Boolean = false,  //
    val subTitleState: Boolean = false,
    val gradeInfor: Int = 0,
    //view
    val isListVisible: Boolean = false,
    val isFilled: Boolean = false,
    val universityResponse: UniversitiesEntity? = null,
    val isUnivCreated : Boolean = false,
    //item
    val isItemSelected : Boolean = false,
    val selectedGrade : Grade? = null,

    val isError : Boolean = false  //TODO 에러 페이지

) : State
