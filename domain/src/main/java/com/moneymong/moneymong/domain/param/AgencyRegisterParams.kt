package com.moneymong.moneymong.domain.param

data class AgencyRegisterParam(
    val name: String,
    val type: AgencyRegisterType,
) {
    enum class AgencyRegisterType {
        CLUB,
        COUNCIL,
    }
}