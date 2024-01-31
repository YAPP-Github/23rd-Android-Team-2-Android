package com.moneymong.moneymong.data.mapper.agency

import com.moneymong.moneymong.domain.entity.agency.AgencyRegisterEntity
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.RegisterAgencyResponse

fun AgencyRegisterParam.toRequest(): AgencyRegisterRequest {
    fun typeParamToTypeRequest(type: AgencyRegisterParam.AgencyRegisterType) = when (type) {
        AgencyRegisterParam.AgencyRegisterType.CLUB -> "IN_SCHOOL_CLUB"
        AgencyRegisterParam.AgencyRegisterType.COUNCIL -> "STUDENT_COUNCIL"
    }

    return AgencyRegisterRequest(
        name = name,
        type = typeParamToTypeRequest(type)
    )
}

fun RegisterAgencyResponse.toEntity() =
        AgencyRegisterEntity(
            id = this.id
        )