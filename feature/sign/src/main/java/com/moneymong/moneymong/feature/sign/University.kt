package com.moneymong.moneymong.feature.sign

data class University (
    val id : Int,
    val univ : String
)

internal val mockUniversities = listOf(
    University(
        id = 1,
        univ = "홍익대학교 서울캠퍼스"
    ),
    University(
        id = 2,
        univ = "홍익대학교 세종캠퍼스"
    ),
    University(
        id = 3,
        univ = "홍대학교"
    ),
    University(
        id = 4,
        univ = "홍뭐시기 대학교"
    )
)