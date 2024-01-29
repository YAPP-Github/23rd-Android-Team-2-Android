package com.moneymong.moneymong.feature.agency.join

import com.moneymong.moneymong.common.base.State

data class AgencyJoinState (
    val isError : Boolean = false,
    val numbers : List<String> = listOf("", "", "", "", "" , ""),
    val codeAccess : Boolean = false
) : State