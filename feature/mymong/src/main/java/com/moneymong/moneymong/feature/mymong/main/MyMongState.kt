package com.moneymong.moneymong.feature.mymong.main

import com.moneymong.moneymong.common.base.State

data class MyMongState(
    val name: String = "",
    val email: String = "",
    val university: String = "",
    val grade: Int = 0,
    val infoErrorMessage: String = "",
    val logoutErrorMessage: String = "",
    val isInfoLoading: Boolean = true,
    val isInfoError: Boolean = false,
    val visibleLogoutDialog: Boolean = false,
    val visibleErrorDialog: Boolean = false,
) : State