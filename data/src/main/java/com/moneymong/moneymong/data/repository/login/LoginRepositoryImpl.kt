package com.moneymong.moneymong.data.repository.login

import android.content.Context
import com.kakao.sdk.user.UserApiClient
import com.moneymong.moneymong.data.datasource.login.LoginRemoteDataSource
import com.moneymong.moneymong.domain.repository.LoginRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginRemoteDataSource,
    @ApplicationContext private val context: Context
) : LoginRepository {
    override suspend fun kakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            dataSource.loginWithKakaoTalk()
        } else {
            dataSource.loginWithKakaoAccount()
        }
    }
}