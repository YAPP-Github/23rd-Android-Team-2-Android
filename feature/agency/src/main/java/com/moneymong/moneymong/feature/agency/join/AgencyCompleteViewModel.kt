package com.moneymong.moneymong.feature.agency.join

import com.moneymong.moneymong.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencyCompleteViewModel @Inject constructor(
) : BaseViewModel<AgencyCompleteState, AgencyCompleteSideEffect>(AgencyCompleteState()) {

    fun isBtnClickChanged(isBtnClicked: Boolean) = intent {
        reduce {
            state.copy(
                isBtnClicked = isBtnClicked
            )
        }
    }
}