package com.moneymong.moneymong.data.mapper.signup

import com.moneymong.moneymong.domain.entity.signup.UnivEntity
import com.moneymong.moneymong.domain.entity.signup.UniversitiesEntity
import com.moneymong.moneymong.domain.entity.signup.University
import com.moneymong.moneymong.domain.entity.signup.UniversityX
import com.moneymong.moneymong.domain.param.signup.SearchQueryParam
import com.moneymong.moneymong.domain.param.signup.UnivParam
import com.moneymong.moneymong.network.request.signup.SearchQueryRequest
import com.moneymong.moneymong.network.request.signup.UnivRequest
import com.moneymong.moneymong.network.response.signup.UnivResponse
import com.moneymong.moneymong.network.response.signup.UniversitiesReponse

fun UnivParam.toRequest() =
    UnivRequest(
        universityName = this.universityName,
        grade = this.grade
    )

fun UnivResponse.toEntity() =
    UnivEntity(
        grade = this.grade,
        universityName = this.universityName
    )

fun SearchQueryParam.toRequest() =
    SearchQueryRequest(
        searchQuery = this.searchQuery
    )

fun UniversitiesReponse.toEntity() =
    UniversitiesEntity(
        universities = this.universities.map { univ ->
            UniversityX(
                id = univ.id,
                schoolName = univ.schoolName
            )
        }
    )
