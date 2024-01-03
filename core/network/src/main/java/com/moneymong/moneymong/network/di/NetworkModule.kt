package com.moneymong.moneymong.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moneymong.moneymong.network.BuildConfig
import com.moneymong.moneymong.network.adapter.ResultCallAdapterFactory
import com.moneymong.moneymong.network.util.MoneyMongTokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkhttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        moneymongAuthenticator: MoneyMongTokenAuthenticator
    ): OkHttpClient {
        val okhttpLoggingClient = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .authenticator(moneymongAuthenticator)
            .addInterceptor(okhttpLoggingClient)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250000L)
            .alwaysReadResponseBody(true)
            .createShortcut(true)
            .build()
    }

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create(gson))
        addCallAdapterFactory(ResultCallAdapterFactory.create())
        client(okHttpClient)
        baseUrl("https://dev.moneymong.site/")
    }.build()

    // TODO Api Provider
}