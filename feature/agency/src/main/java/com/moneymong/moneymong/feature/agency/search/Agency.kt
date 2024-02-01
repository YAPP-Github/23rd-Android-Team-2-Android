package com.moneymong.moneymong.feature.agency.search

import com.moneymong.moneymong.domain.entity.agency.AgencyGetEntity
import com.moneymong.moneymong.domain.entity.agency.MyAgencyEntity
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam

data class Agency(
    val id: Long,
    val type: AgencyType,
    val name: String,
    val memberCount: Int
)

fun AgencyGetEntity.toAgency(): Agency {
    return Agency(
        id = this.id,
        type = when (this.type) {
            AgencyGetEntity.AgencyType.CLUB -> AgencyType.CLUB
            AgencyGetEntity.AgencyType.COUNCIL -> AgencyType.COUNCIL
        },
        name = this.name,
        memberCount = this.headCount
    )
}

fun MyAgencyEntity.toAgency(): Agency {
    return Agency(
        id = this.id.toLong(),
        type = when (this.type) {
            "IN_SCHOOL_CLUB" -> AgencyType.CLUB
            "STUDENT_COUNCIL" -> AgencyType.COUNCIL
            else -> throw IllegalArgumentException("Unknown type: $type")
        },
        name = this.name,
        memberCount = this.headCount
    )
}

enum class AgencyType(val text: String) {
    CLUB(text = "동아리"),
    COUNCIL(text = "학생회");

    fun toParam(): AgencyRegisterParam.AgencyRegisterType = when (this) {
        CLUB -> AgencyRegisterParam.AgencyRegisterType.CLUB
        COUNCIL -> AgencyRegisterParam.AgencyRegisterType.COUNCIL
    }
}