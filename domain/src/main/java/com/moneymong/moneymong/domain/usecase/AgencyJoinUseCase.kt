package com.moneymong.moneymong.domain.usecase

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.agency.AgencyJoinEntity
import com.moneymong.moneymong.domain.param.agency.AgencyJoinParam
import com.moneymong.moneymong.domain.repository.AgencyRepository
import javax.inject.Inject

class AgencyJoinUseCase @Inject constructor(
    private val agencyRepository : AgencyRepository
) : BaseUseCase<AgencyJoinParam, Result<AgencyJoinEntity>>()
    {

        override suspend fun invoke(data: AgencyJoinParam): Result<AgencyJoinEntity> {
            return agencyRepository.agencyCodeNumbers(data)
    }

}