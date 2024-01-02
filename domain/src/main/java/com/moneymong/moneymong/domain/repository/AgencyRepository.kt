package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam

interface AgencyRepository {

    suspend fun registerAgency(param: AgencyRegisterParam): Result<Unit>
}