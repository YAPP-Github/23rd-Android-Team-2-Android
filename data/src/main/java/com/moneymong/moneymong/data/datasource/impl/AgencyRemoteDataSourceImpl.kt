package com.moneymong.moneymong.data.datasource.impl

import com.moneymong.moneymong.data.datasource.AgencyRemoteDataSource
import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.AgenciesGetResponse
import javax.inject.Inject

class AgencyRemoteDataSourceImpl @Inject constructor(
    private val moneyMongApi: MoneyMongApi
) : AgencyRemoteDataSource {

    override suspend fun registerAgency(request: AgencyRegisterRequest): Result<Unit> {
        return moneyMongApi.registerAgency(request = request)
    }

    override suspend fun getAgencies(page: Int, size: Int): Result<AgenciesGetResponse> {
        return moneyMongApi.getAgencies(page = page, size = size)
    }
}