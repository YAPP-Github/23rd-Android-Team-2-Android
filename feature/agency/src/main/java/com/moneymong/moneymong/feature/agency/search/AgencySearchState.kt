package com.moneymong.moneymong.feature.agency.search

import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.feature.agency.search.component.AgencyBottomSheetType

data class AgencySearchState(
    val visibleBottomSheet: Boolean = false,
    val registerType: AgencyBottomSheetType? = null,
) : State