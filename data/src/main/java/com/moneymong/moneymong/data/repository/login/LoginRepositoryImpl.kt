package com.moneymong.moneymong.data.repository.login

import android.content.Context
import com.kakao.sdk.user.UserApiClient
import com.moneymong.moneymong.data.datasource.login.LoginRemoteDataSource
import com.moneymong.moneymong.domain.util.LoginCallback
import com.moneymong.moneymong.domain.repository.LoginRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    @ApplicationContext private val context: Context
) : LoginRepository {
    override suspend fun kakaoLogin(callback: LoginCallback) {
//        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
//            remoteDataSource.loginWithKakaoTalk(callback)
//        } else {
            remoteDataSource.loginWithKakaoAccount(callback)
//        }
    }
}