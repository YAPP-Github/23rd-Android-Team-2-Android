package com.moneymong.moneymong.feature.agency.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.moneymong.moneymong.domain.usecase.agency.GetAgenciesUseCase
import com.moneymong.moneymong.feature.agency.toAgency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class AgencySearchViewModel @Inject constructor(
    getAgenciesUseCase: GetAgenciesUseCase,
) : ViewModel() {

    val agencies = getAgenciesUseCase().cachedIn(viewModelScope).map { pagingData ->
        pagingData.map {
            it.toAgency()
        }
    }
}