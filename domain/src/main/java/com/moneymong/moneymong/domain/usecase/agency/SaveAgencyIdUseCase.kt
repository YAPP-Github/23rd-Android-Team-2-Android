package com.moneymong.moneymong.domain.usecase.agency

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.AgencyRepository
import javax.inject.Inject

class SaveAgencyIdUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository
): BaseUseCase<Int, Unit>() {
    override suspend fun invoke(data: Int) =
        agencyRepository.saveAgencyId(data)
}