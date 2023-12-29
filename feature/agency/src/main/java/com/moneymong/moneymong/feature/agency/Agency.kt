package com.moneymong.moneymong.feature.agency

import com.moneymong.moneymong.domain.param.AgencyRegisterParam

data class Agency(
    val type: AgencyType,
    val name: String,
    val memberCount: Int
)

enum class AgencyType(val text: String) {
    CLUB(text = "동아리"),
    COUNCIL(text = "학생회");

    fun toParam(): AgencyRegisterParam.AgencyRegisterType = when (this) {
        CLUB -> AgencyRegisterParam.AgencyRegisterType.CLUB
        COUNCIL -> AgencyRegisterParam.AgencyRegisterType.COUNCIL
    }
}


internal val mockAgencies = listOf(
    Agency(
        type = AgencyType.CLUB,
        name = "나는 매우 길다.나는 매우 길다.나는 매우 길다.나는 매우 길다.나는 매우 길다.나는 매우 길다.",
        memberCount = 10
    ),

    Agency(
        type = AgencyType.CLUB,
        name = "농구 동아리",
        memberCount = 10
    ),
    Agency(
        type = AgencyType.CLUB,
        name = "축구 동아리",
        memberCount = 564334
    ),
    Agency(
        type = AgencyType.CLUB,
        name = "배구 동아리",
        memberCount = 13
    ),
    Agency(
        type = AgencyType.CLUB,
        name = "테니스 동아리",
        memberCount = 321
    ),
    Agency(
        type = AgencyType.COUNCIL,
        name = "독어독문 학생회",
        memberCount = 1
    ),
    Agency(
        type = AgencyType.CLUB,
        name = "탁구 동아리",
        memberCount = 10
    ),
    Agency(
        type = AgencyType.COUNCIL,
        name = "컴퓨터공학과 학생회",
        memberCount = 43
    ),
)