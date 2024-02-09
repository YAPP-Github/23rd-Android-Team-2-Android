package com.moneymong.moneymong.data.datasource.agency

import com.moneymong.moneymong.network.request.agency.AgencyJoinRequest
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.AgencyJoinResponse
import com.moneymong.moneymong.network.response.agency.AgenciesGetResponse
import com.moneymong.moneymong.network.response.agency.MyAgencyResponse
import com.moneymong.moneymong.network.response.agency.RegisterAgencyResponse

interface AgencyRemoteDataSource {
    suspend fun registerAgency(request: AgencyRegisterRequest): Result<RegisterAgencyResponse>
    suspend fun getAgencies(page: Int, size: Int): Result<AgenciesGetResponse>
    suspend fun fetchMyAgencyList(): Result<List<MyAgencyResponse>>
    suspend fun agencyCodeNumbers(agencyId : Long, data: AgencyJoinRequest) : Result<AgencyJoinResponse>
}