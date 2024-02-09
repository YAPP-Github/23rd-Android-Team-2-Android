package com.moneymong.moneymong

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoneyMongApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val NATIVE_APP_KEY = BuildConfig.NATIVE_APP_KEY
        KakaoSdk.init(this, NATIVE_APP_KEY)

    }
}
