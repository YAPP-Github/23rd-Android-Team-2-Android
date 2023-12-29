package com.moneymong.moneymong.network.source

import com.moneymong.moneymong.network.request.AgencyRegisterRequest

interface AgencyRemoteDataSource {

    suspend fun registerAgency(request: AgencyRegisterRequest): Result<Unit>
}