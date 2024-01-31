package com.moneymong.moneymong.data.datasource.agency

interface AgencyLocalDataSource {
    suspend fun saveAgencyId(agencyId: Int)
    suspend fun fetchAgencyId(): Int
}