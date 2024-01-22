package com.moneymong.moneymong.data.datasource

import com.moneymong.moneymong.network.request.agency.AgencyJoinRequest
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.AgencyJoinResponse
import com.moneymong.moneymong.network.response.agency.AgenciesGetResponse

interface AgencyRemoteDataSource {

    suspend fun registerAgency(request: AgencyRegisterRequest): Result<Unit>

    suspend fun getAgencies(page: Int, size: Int): Result<AgenciesGetResponse>

    suspend fun agencyCodeNumbers(data: AgencyJoinRequest) : Result<AgencyJoinResponse>

}