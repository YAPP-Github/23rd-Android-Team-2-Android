package com.moneymong.moneymong.data.datasource

import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.AgenciesGetResponse

interface AgencyRemoteDataSource {

    suspend fun registerAgency(request: AgencyRegisterRequest): Result<Unit>

    suspend fun getAgencies(page: Int, size: Int): Result<AgenciesGetResponse>
}