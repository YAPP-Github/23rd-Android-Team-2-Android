package com.moneymong.moneymong.common.error

sealed class MoneyMongError : Throwable() {

    data object UnExpectedError : MoneyMongError() {
        override val message: String = "머니몽 서비스에 문제가 발생했어요"
    }

    data object NetworkConnectionError : MoneyMongError() {
        override val message: String = "네트워크 연결을 확인해주세요"
    }
}