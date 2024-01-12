package com.moneymong.moneymong.feature.agency

import com.moneymong.moneymong.domain.entity.agency.AgencyGetEntity
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam

data class Agency(
    val id: Int,
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

enum class AgencyType(val text: String) {
    CLUB(text = "동아리"),
    COUNCIL(text = "학생회");

    fun toParam(): AgencyRegisterParam.AgencyRegisterType = when (this) {
        CLUB -> AgencyRegisterParam.AgencyRegisterType.CLUB
        COUNCIL -> AgencyRegisterParam.AgencyRegisterType.COUNCIL
    }
}