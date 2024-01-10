package com.moneymong.moneymong.data.mapper.login

import com.moneymong.moneymong.domain.entity.login.RefreshTokenEntity
import com.moneymong.moneymong.network.response.login.RefreshTokenResponse

fun RefreshTokenResponse.toEntity() =
    RefreshTokenEntity(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )