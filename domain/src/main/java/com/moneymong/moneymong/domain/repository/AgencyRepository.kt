package com.moneymong.moneymong.domain.repository

import com.moneymong.moneymong.domain.param.AgencyRegisterParam

interface AgencyRepository {

    suspend fun registerAgency(param: AgencyRegisterParam): Result<Unit>
}