package com.moneymong.moneymong.common.error

sealed interface HttpError : MoneyMongError {

    /**
     * 400 Bad Request
     */
    data class BadRequestError(override val message: String) : HttpError

    /**
     * 401 Unauthorized
     */
    data class UnauthorizedError(override val message: String) : HttpError

    /**
     * 403 Forbidden
     */
    data class ForbiddenError(override val message: String) : HttpError

    /**
     * 404 Not Found
     */
    data class NotFoundError(override val message: String) : HttpError

    /**
     * 500 Internal Server Error
     */
    data class InternalServerError(override val message: String) : HttpError
}
