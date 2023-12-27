package com.moneymong.moneymong

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoneyMongApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}