package com.moneymong.moneymong.network.source

import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest

interface AgencyRemoteDataSource {

    suspend fun registerAgency(request: AgencyRegisterRequest): Result<Unit>
}