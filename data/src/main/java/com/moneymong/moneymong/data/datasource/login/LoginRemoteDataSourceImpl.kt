package com.moneymong.moneymong.data.datasource.login

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.moneymong.moneymong.data.util.LoginType
import com.moneymong.moneymong.network.api.login.AccessTokenApi
import com.moneymong.moneymong.network.request.login.RefreshTokenRequest
import com.moneymong.moneymong.network.request.login.TokenRequest
import com.moneymong.moneymong.network.response.login.RefreshTokenResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val accessTokenApi: AccessTokenApi,
    private val loginLocalDataSourceImpl: LoginLocalDataSourceImpl,
) : LoginRemoteDataSource {

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.d("this", "카카오계정으로 로그인 실패 : $error")
        } else if (token != null) {
            //TODO: 최종적으로 카카오로그인 및 유저정보 가져온 결과
            UserApiClient.instance.me { user, error ->
                Log.d(
                    "this",
                    "카카오계정으로 로그인 성공 \n\n " +
                            "accesstoken: ${token.accessToken} \n\n " +
                            "accesstokenExpireAt : ${token.accessTokenExpiresAt} \n\n" +
                            "refreshtoken : ${token.refreshToken} \n\n" +
                            "refreshtokenExpireAt : ${token.refreshTokenExpiresAt} \n\n" +
                            "me: $user",
                )
                CoroutineScope(Dispatchers.IO).launch {
                    sendToken(LoginType.KAKAO.type, token.accessToken)
                }
            }
        }
    }

    override suspend fun loginWithKakaoTalk(
    ) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.d("this", "카카오톡으로 로그인 실패 : $error")

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            } else if (token != null) {
                Log.d("this", "카카오톡으로 로그인 성공 ${token.accessToken}")
            }
        }
    }

    override suspend fun loginWithKakaoAccount(
    ) {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }

    private suspend fun sendToken(type : String, accessToken: String) {
        Log.d("확인", "토큰을 보냈습니다.")
        accessTokenApi.accessTokenApi(TokenRequest(type, accessToken))
            .onSuccess {
                Log.d(
                    "success", "${it.accessToken}\n" +
                            "${it.refreshToken}\n" +
                            "${it.loginSuccess}\n" +
                            "${it.schoolInfoExist}"
                )
                loginLocalDataSourceImpl.setDataStore(
                    it.accessToken,
                    it.refreshToken,
                    it.loginSuccess,
                    it.schoolInfoExist
                )
            }
            .onFailure {
                Log.d("failure", it.message.toString())
                //TODO
            }

    }



}