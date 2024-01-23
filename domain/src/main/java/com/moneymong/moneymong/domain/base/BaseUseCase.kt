package com.moneymong.moneymong.domain.base

abstract class BaseUseCase<P, R> {
    abstract suspend operator fun invoke(data: P): R
}