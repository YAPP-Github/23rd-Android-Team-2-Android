package com.moneymong.moneymong.feature.mymong.main

import com.moneymong.moneymong.common.base.State

data class MyMongState(
    val name: String = "",
    val email: String = "",
    val university: String = "",
    val grade: Int = 0,

    val isInfoLoading: Boolean = true,
    val isInfoError: Boolean = false,
    val infoErrorMessage: String = "",

    val visibleLogoutDialog: Boolean = false,
    val version: String = "1.0.0"
) : State