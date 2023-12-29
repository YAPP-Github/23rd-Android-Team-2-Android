package com.moneymong.moneymong.data.mapper

import com.moneymong.moneymong.domain.param.AgencyRegisterParams
import com.moneymong.moneymong.network.request.AgencyRegisterRequest

fun AgencyRegisterParams.toRequest(): AgencyRegisterRequest {
    fun typeParamsToTypeRequest(type: AgencyRegisterParams.AgencyRegisterType) = when (type) {
        AgencyRegisterParams.AgencyRegisterType.CLUB -> "IN_SCHOOL_CLUB"
        AgencyRegisterParams.AgencyRegisterType.COUNCIL -> "STUDENT_COUNCIL"
    }

    return AgencyRegisterRequest(
        name = name,
        type = typeParamsToTypeRequest(type)
    )
}