package com.moneymong.moneymong.domain.entity.agency


data class AgencyGetEntity(
    val id: Int,
    val name: String,
    val headCount: Int,
    val type: AgencyType
) {
    enum class AgencyType {
        CLUB,
        COUNCIL,
    }
}