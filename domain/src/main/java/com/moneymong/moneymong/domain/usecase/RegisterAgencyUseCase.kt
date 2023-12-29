package com.moneymong.moneymong.domain.usecase


import com.moneymong.moneymong.domain.param.AgencyRegisterParams
import com.moneymong.moneymong.domain.repository.AgencyRepository
import javax.inject.Inject

class RegisterAgencyUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
) {
    suspend operator fun invoke(params: AgencyRegisterParams): Result<Unit> {
        return agencyRepository.registerAgency(params = params)
    }
}