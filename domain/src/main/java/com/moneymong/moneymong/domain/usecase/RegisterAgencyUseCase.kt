package com.moneymong.moneymong.domain.usecase


import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import com.moneymong.moneymong.domain.repository.AgencyRepository
import javax.inject.Inject

class RegisterAgencyUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) : BaseUseCase<AgencyRegisterParam, Result<Unit>>() {
    override suspend operator fun invoke(data: AgencyRegisterParam): Result<Unit> {
        return agencyRepository.registerAgency(param = data)
    }
}