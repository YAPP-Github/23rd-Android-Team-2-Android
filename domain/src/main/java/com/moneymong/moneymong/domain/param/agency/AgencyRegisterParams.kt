package com.moneymong.moneymong.domain.param.agency

data class AgencyRegisterParam(
    val name: String,
    val type: AgencyRegisterType,
) {
    enum class AgencyRegisterType {
        CLUB,
        COUNCIL,
    }
}