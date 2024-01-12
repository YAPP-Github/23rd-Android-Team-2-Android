package com.moneymong.moneymong.domain.repository

import androidx.paging.PagingData
import com.moneymong.moneymong.domain.entity.agency.AgencyGetEntity
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import kotlinx.coroutines.flow.Flow

interface AgencyRepository {

    suspend fun registerAgency(param: AgencyRegisterParam): Result<Unit>

    fun getAgencies(): Flow<PagingData<AgencyGetEntity>>
}