package com.moneymong.moneymong.feature.agency.join

import com.moneymong.moneymong.common.base.State

data class AgencyCompleteState (
    val isBtnClicked : Boolean = false
) : State