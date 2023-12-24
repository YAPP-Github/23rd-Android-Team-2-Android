package com.moneymong.moneymong.common.error

sealed interface MoneyMongError {

    val message: String

    data object UnExpectedError : MoneyMongError {
        override val message: String = "머니몽 서비스에 문제가 발생했어요"
    }

    data object NetworkConnectionError : MoneyMongError {
        override val message: String = "네트워크 연결을 확인해주세요"
    }

    fun getErrorByStatusCode(statusCode: Int, message: String): MoneyMongError {
        return when (statusCode) {
            400 -> HttpError.BadRequestError(message = message)
            401 -> HttpError.UnauthorizedError(message = message)
            403 -> HttpError.ForbiddenError(message = message)
            404 -> HttpError.NotFoundError(message = message)
            500 -> HttpError.InternalServerError(message = message)
            else -> UnExpectedError
        }
    }
}