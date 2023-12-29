package com.moneymong.moneymong.data.mapper

import com.moneymong.moneymong.domain.param.AgencyRegisterParam
import com.moneymong.moneymong.network.request.AgencyRegisterRequest

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