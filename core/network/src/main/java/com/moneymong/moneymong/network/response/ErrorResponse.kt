package com.moneymong.moneymong.network.response

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    val status: Int,
    val message: String
)

fun fromJsonToErrorResponse(json: String): ErrorResponse {
    return Gson().fromJson(json, ErrorResponse::class.java)
}