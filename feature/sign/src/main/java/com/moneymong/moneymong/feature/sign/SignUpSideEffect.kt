package com.moneymong.moneymong.feature.sign

import com.moneymong.moneymong.common.base.SideEffect

sealed class SignUpSideEffect : SideEffect {
    data class UniversitiesApi(val univ: String) : SignUpSideEffect()
    data class CreateUniversityApi(
        val universityName : String,
        val grade: Int
    ) : SignUpSideEffect()
}