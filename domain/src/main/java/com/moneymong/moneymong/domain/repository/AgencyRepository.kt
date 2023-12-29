package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.param.AgencyRegisterParams

interface AgencyRepository {

    suspend fun registerAgency(params: AgencyRegisterParams): Result<Unit>
}