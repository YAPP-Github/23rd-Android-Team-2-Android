package com.moneymong.moneymong.data.api.login

import com.moneymong.moneymong.data.response.login.kakaoLoginResponse
import retrofit2.http.POST


interface kakaoLoginApi {

    @POST("oauth2/authorization/kakao")
    suspend fun kakaoLogin(): Result<Unit>

}