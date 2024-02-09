package com.moneymong.moneymong.domain.usecase.agency

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.agency.AgencyJoinEntity
import com.moneymong.moneymong.domain.param.agency.AgencyJoinParam
import com.moneymong.moneymong.domain.repository.AgencyRepository
import javax.inject.Inject

class AgencyJoinUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) {
    suspend fun invoke(agencyId: Long, data: AgencyJoinParam): Result<AgencyJoinEntity> {
        return agencyRepository.agencyCodeNumbers(agencyId, data)
    }

}