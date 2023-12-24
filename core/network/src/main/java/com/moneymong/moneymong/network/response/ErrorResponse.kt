package com.moneymong.moneymong.network.response

import com.google.gson.Gson

data class ErrorResponse(
    val status: Int,
    val message: String
)

fun fromJsonToErrorResponse(json: String): ErrorResponse {
    return Gson().fromJson(json, ErrorResponse::class.java)
}