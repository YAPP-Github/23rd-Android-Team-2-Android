package com.moneymong.moneymong.common.result

import com.moneymong.moneymong.common.error.MoneyMongError

sealed interface MoneyMongResult<out T> {
    data class Success<T>(val data: T) : MoneyMongResult<T>
    data class Failure(val error: MoneyMongError) : MoneyMongResult<Nothing>
}

inline fun <R, T> MoneyMongResult<T>.map(
    transform: (value: T?) -> R
): MoneyMongResult<R> {
    return when (this) {
        is MoneyMongResult.Success -> MoneyMongResult.Success(transform(data))
        is MoneyMongResult.Failure -> MoneyMongResult.Failure(error)
    }
}

inline fun <T> MoneyMongResult<T>.onSuccess(
    action: (value: T) -> Unit
): MoneyMongResult<T> {
    if (this is MoneyMongResult.Success) action(data)
    return this
}

inline fun <T> MoneyMongResult<T>.onFailure(
    action: (error: MoneyMongError) -> Unit
): MoneyMongResult<T> {
    if (this is MoneyMongResult.Failure) action(error)
    return this
}