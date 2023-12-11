package com.moneymong.moneymong.feature.agency

data class Agency(
    val type: AgencyType,
    val name: String,
    val memberCount: Int
)

enum class AgencyType(val text: String) {
    CLUB(text = "동아리"),
    COUNCIL(text = "학생회"),
}