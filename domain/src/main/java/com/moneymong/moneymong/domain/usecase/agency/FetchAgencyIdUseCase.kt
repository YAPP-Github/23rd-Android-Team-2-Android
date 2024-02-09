package com.moneymong.moneymong.domain.usecase.agency

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.AgencyRepository
import javax.inject.Inject

class FetchAgencyIdUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
): BaseUseCase<Unit, Int>() {
    override suspend fun invoke(data: Unit): Int =
        agencyRepository.fetchAgencyId()
}