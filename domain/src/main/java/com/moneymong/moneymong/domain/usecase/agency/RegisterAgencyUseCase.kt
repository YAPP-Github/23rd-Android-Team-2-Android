package com.moneymong.moneymong.domain.usecase.agency


import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.agency.AgencyRegisterEntity
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import com.moneymong.moneymong.domain.repository.AgencyRepository
import javax.inject.Inject

class RegisterAgencyUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) : BaseUseCase<AgencyRegisterParam, Result<AgencyRegisterEntity>>() {
    override suspend operator fun invoke(data: AgencyRegisterParam): Result<AgencyRegisterEntity> {
        return agencyRepository.registerAgency(param = data)
    }
}