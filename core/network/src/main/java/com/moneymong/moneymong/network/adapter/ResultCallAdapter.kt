package com.moneymong.moneymong.network.adapter

import com.moneymong.moneymong.common.error.MoneyMongError
import com.moneymong.moneymong.common.error.getErrorByStatusCode
import com.moneymong.moneymong.common.result.MoneyMongResult
import com.moneymong.moneymong.network.response.fromJsonToErrorResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

internal class ResultCallAdapter(
    private val responseType: Type
) : CallAdapter<Type, Call<MoneyMongResult<Type>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<Type>): Call<MoneyMongResult<Type>> = ResultCall(call)
}


private class ResultCall<T>(
    private val delegate: Call<T>,
) : Call<MoneyMongResult<T>> {

    override fun enqueue(callback: Callback<MoneyMongResult<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(this@ResultCall, Response.success(response.toMoneyMongResult()))
            }

            private fun Response<T>.toMoneyMongResult(): MoneyMongResult<T> {
                val body = body()
                val errorBody = errorBody()?.string()

                if (isSuccessful && body != null) {
                    return MoneyMongResult.Success(body)
                }

                if (body == null || errorBody == null) {
                    return MoneyMongResult.Failure(MoneyMongError.UnExpectedError)
                }

                val errorResponse = fromJsonToErrorResponse(errorBody)
                val httpError = getErrorByStatusCode(
                    statusCode = errorResponse.status,
                    message = errorResponse.message
                )
                return MoneyMongResult.Failure(httpError)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val failure = when (t) {
                    is IOException -> MoneyMongError.NetworkConnectionError
                    else -> MoneyMongError.UnExpectedError
                }
                callback.onResponse(
                    this@ResultCall,
                    Response.success(MoneyMongResult.Failure(failure))
                )
            }
        })
    }


    override fun clone(): Call<MoneyMongResult<T>> = ResultCall(delegate.clone())

    override fun execute(): Response<MoneyMongResult<T>> = throw UnsupportedOperationException()

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}