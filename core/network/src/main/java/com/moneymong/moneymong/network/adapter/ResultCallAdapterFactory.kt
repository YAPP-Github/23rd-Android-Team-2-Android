package com.moneymong.moneymong.network.adapter

import com.moneymong.moneymong.common.result.MoneyMongResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapterFactory private constructor() : CallAdapter.Factory() {

    companion object {
        fun create() = ResultCallAdapterFactory()
    }


    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        val wrapperType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(wrapperType) != MoneyMongResult::class.java) {
            return null
        }

        val responseType = getParameterUpperBound(0, wrapperType as ParameterizedType)
        return ResultCallAdapter(responseType)
    }
}
