package com.moneymong.moneymong.data.mapper.agency

import com.moneymong.moneymong.domain.entity.agency.AgencyGetEntity
import com.moneymong.moneymong.network.response.agency.AgencyGetResponse

fun AgencyGetResponse.toEntity() = AgencyGetEntity(
    id = id,
    name = name,
    headCount = headCount,
    type = when (type) {
        "IN_SCHOOL_CLUB" -> AgencyGetEntity.AgencyType.CLUB
        "STUDENT_COUNCIL" -> AgencyGetEntity.AgencyType.COUNCIL
//        else -> throw IllegalArgumentException("Unknown type: $type")
        else -> AgencyGetEntity.AgencyType.CLUB
    }
)