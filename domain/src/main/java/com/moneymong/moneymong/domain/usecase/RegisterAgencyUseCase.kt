package com.moneymong.moneymong.domain.usecase


import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import com.moneymong.moneymong.domain.repository.AgencyRepository
import javax.inject.Inject

class RegisterAgencyUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) {
    suspend operator fun invoke(param: AgencyRegisterParam): Result<Unit> {
        return agencyRepository.registerAgency(param = param)
    }
}