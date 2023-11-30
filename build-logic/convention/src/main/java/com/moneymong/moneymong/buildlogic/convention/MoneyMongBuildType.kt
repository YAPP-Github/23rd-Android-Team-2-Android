package com.moneymong.moneymong.buildlogic.convention

enum class MoneyMongBuildType(val applicationIdSuffix: String) {
    DEBUG(".tb"),
    RELEASE(".live")
}