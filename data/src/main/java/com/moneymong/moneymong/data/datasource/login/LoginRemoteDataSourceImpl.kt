package com.moneymong.moneymong.data.datasource.login

import android.content.Context
import android.util.Log
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.moneymong.moneymong.data.datasource.user.UserLocalDataSource
import com.moneymong.moneymong.data.util.LoginType
import com.moneymong.moneymong.domain.util.LoginCallback
import com.moneymong.moneymong.network.api.AccessTokenApi
import com.moneymong.moneymong.network.api.UserApi
import com.moneymong.moneymong.network.request.login.TokenRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val accessTokenApi: AccessTokenApi,
    private val userApi: UserApi,
    private val loginLocalDataSourceImpl: LoginLocalDataSourceImpl,
    private val userLocalDataSource: UserLocalDataSource,
) : LoginRemoteDataSource {

    override suspend fun loginWithKakaoTalk(callback: LoginCallback) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.d("this", "카카오톡으로 로그인 실패 : $error")
                CoroutineScope(Dispatchers.IO).launch {
                    callback.onLoginFailure(exception = Exception())
                }

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                CoroutineScope(Dispatchers.IO).launch {
                    loginWithKakaoAccount(callback)
                }
            } else if (token != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    sendToken(LoginType.KAKAO.type, token.accessToken)
                    callback.onLoginSuccess()
                }
                Log.d("this", "카카오톡으로 로그인 성공 ${token.accessToken}")
            }
        }
    }


    override suspend fun loginWithKakaoAccount(callback: LoginCallback) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                Log.d("this", "카카오계정으로 로그인 실패 : $error")
                CoroutineScope(Dispatchers.IO).launch {
                    callback.onLoginFailure(Exception(error))
                }
            } else if (token != null) {
                Log.d("this", "카카오계정으로 로그인 성공, accessToken: ${token.accessToken}")
                UserApiClient.instance.me { user, error ->
                    if (error == null) {
                        CoroutineScope(Dispatchers.IO).launch {
                            sendToken(LoginType.KAKAO.type, token.accessToken)
                            callback.onLoginSuccess()
                        }
                    } else {
                        CoroutineScope(Dispatchers.IO).launch {
                            callback.onLoginFailure(Exception(error))
                        }
                    }
                }
            }
        }
    }


    private suspend fun sendToken(type: String, accessToken: String) {
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
                saveUserInfo()
            }
            .onFailure {
                Log.d("failure", it.message.toString())
                //TODO
            }
    }

    private suspend fun saveUserInfo() {
        userApi.getMyInfo()
            .onSuccess {
                userLocalDataSource.saveUserId(it.id.toInt())
                userLocalDataSource.saveUserNickName(it.name)
            }.onFailure {
                userLocalDataSource.saveUserId(0)
                userLocalDataSource.saveUserNickName("에러가 발생했습니다.")
            }
    }
}