package com.moneymong.moneymong.feature.sign.sideeffect

import com.moneymong.moneymong.common.base.SideEffect

sealed interface SignUpSideEffect : SideEffect {
    data class UniversitiesApi(val univ: String) : SignUpSideEffect
    data class CreateUniversityApi(
        val universityName : String,
        val grade: Int
    ) : SignUpSideEffect
}