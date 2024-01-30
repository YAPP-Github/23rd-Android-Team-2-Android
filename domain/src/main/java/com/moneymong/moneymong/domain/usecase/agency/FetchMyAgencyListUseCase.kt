package com.moneymong.moneymong.domain.usecase.agency

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.agency.MyAgencyEntity
import com.moneymong.moneymong.domain.repository.AgencyRepository
import javax.inject.Inject

class FetchMyAgencyListUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
): BaseUseCase<Unit, Result<List<MyAgencyEntity>>>() {
    override suspend fun invoke(data: Unit): Result<List<MyAgencyEntity>> =
        agencyRepository.fetchMyAgencyList()
}