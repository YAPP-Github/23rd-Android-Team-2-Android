package com.moneymong.moneymong.domain.usecase.agency

import androidx.paging.PagingData
import com.moneymong.moneymong.domain.entity.agency.AgencyGetEntity
import com.moneymong.moneymong.domain.repository.AgencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAgenciesUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) {
    operator fun invoke(): Flow<PagingData<AgencyGetEntity>> {
        return agencyRepository.getAgencies()
    }
}