package com.moneymong.moneymong.domain.param

data class AgencyRegisterParams(
    val name: String,
    val type: AgencyRegisterType,
) {
    enum class AgencyRegisterType {
        CLUB,
        COUNCIL,
    }
}