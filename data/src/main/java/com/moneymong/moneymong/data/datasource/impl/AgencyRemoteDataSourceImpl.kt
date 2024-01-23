package com.moneymong.moneymong.data.datasource.impl

import com.moneymong.moneymong.data.datasource.AgencyRemoteDataSource
import com.moneymong.moneymong.network.api.AgencyApi
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.AgenciesGetResponse
import javax.inject.Inject

class AgencyRemoteDataSourceImpl @Inject constructor(
    private val agencyApi: AgencyApi
) : AgencyRemoteDataSource {

    override suspend fun registerAgency(request: AgencyRegisterRequest): Result<Unit> {
        return agencyApi.registerAgency(request = request)
    }

    override suspend fun getAgencies(page: Int, size: Int): Result<AgenciesGetResponse> {
        return agencyApi.getAgencies(page = page, size = size)
    }
}