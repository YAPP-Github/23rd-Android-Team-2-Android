package com.moneymong.moneymong.network.request.agency

import com.google.gson.annotations.SerializedName

data class AgencyRegisterRequest(
    val name: String,
    @SerializedName("agencyType")
    val type: String
)