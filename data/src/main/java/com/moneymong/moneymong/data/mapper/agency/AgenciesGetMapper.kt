package com.moneymong.moneymong.data.mapper.agency

import com.moneymong.moneymong.domain.entity.agency.AgencyGetEntity
import com.moneymong.moneymong.domain.entity.agency.MyAgencyEntity
import com.moneymong.moneymong.network.response.agency.AgencyGetResponse
import com.moneymong.moneymong.network.response.agency.MyAgencyResponse

fun AgencyGetResponse.toEntity() = AgencyGetEntity(
    id = id,
    name = name,
    headCount = headCount,
    type = when (type) {
        "IN_SCHOOL_CLUB" -> AgencyGetEntity.AgencyType.CLUB
        "STUDENT_COUNCIL" -> AgencyGetEntity.AgencyType.COUNCIL
        else -> throw IllegalArgumentException("Unknown type: $type")
    }
)

fun MyAgencyResponse.toEntity() =
    MyAgencyEntity(
        id = this.id,
        name = this.name,
        headCount = this.headCount,
        type = this.type
    )