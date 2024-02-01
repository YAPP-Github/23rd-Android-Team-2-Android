package com.moneymong.moneymong.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moneymong.moneymong.network.BuildConfig
import com.moneymong.moneymong.network.adapter.ResultCallAdapterFactory
import com.moneymong.moneymong.network.api.AccessTokenApi
import com.moneymong.moneymong.network.api.UniversityApi
import com.moneymong.moneymong.network.util.AuthInterceptor
import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.api.AgencyApi
import com.moneymong.moneymong.network.api.ClovaApi
import com.moneymong.moneymong.network.api.LedgerApi
import com.moneymong.moneymong.network.api.LedgerDetailApi
import com.moneymong.moneymong.network.api.MemberApi
import com.moneymong.moneymong.network.api.UserApi
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
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ClovaRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MoneyMongRetrofit

    @Provides
    fun provideOkhttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        moneymongAuthenticator: MoneyMongTokenAuthenticator,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val okhttpLoggingClient = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(okhttpLoggingClient)
            .addInterceptor(chuckerInterceptor)
            .authenticator(moneymongAuthenticator)
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
    @MoneyMongRetrofit
    fun provideMoneyMongClient(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        baseUrl(BuildConfig.MONEYMONG_BASE_URL)
        addConverterFactory(GsonConverterFactory.create(gson))
        addCallAdapterFactory(ResultCallAdapterFactory.create())
    }.build()
    
    @Provides
    @Singleton
    @ClovaRetrofit
    fun provideClovaClient(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder().apply {
            client(okHttpClient)
            baseUrl(BuildConfig.CLOVA_OCR_DOCUMENT_BASEURL)
            addConverterFactory(GsonConverterFactory.create(gson))
            addCallAdapterFactory(ResultCallAdapterFactory.create())
        }.build()

    @Provides
    fun provideMoneyMongApi(@MoneyMongRetrofit retrofit: Retrofit): MoneyMongApi =
        retrofit.create(MoneyMongApi::class.java)
        
    @Provides
    fun provideUnivApi(@MoneyMongRetrofit retrofit: Retrofit): UniversityApi =
        retrofit.create(UniversityApi::class.java)


    @Provides
    fun provideLoginApi(@MoneyMongRetrofit retrofit: Retrofit): AccessTokenApi {
        return retrofit.create(AccessTokenApi::class.java)
    }

    @Provides
    fun provideAgencyApi(@MoneyMongRetrofit retrofit: Retrofit): AgencyApi =
        retrofit.create(AgencyApi::class.java)

    @Provides
    fun provideLedgerApi(@MoneyMongRetrofit retrofit: Retrofit): LedgerApi =
        retrofit.create(LedgerApi::class.java)

    @Provides
    fun provideLedgerDetailApi(@MoneyMongRetrofit retrofit: Retrofit): LedgerDetailApi =
        retrofit.create(LedgerDetailApi::class.java)

    @Provides
    fun provideUserApi(@MoneyMongRetrofit retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    fun provideMemberApi(@MoneyMongRetrofit retrofit: Retrofit): MemberApi =
        retrofit.create(MemberApi::class.java)

    @Provides
    fun provideClovaApi(@ClovaRetrofit retrofit: Retrofit): ClovaApi =
        retrofit.create(ClovaApi::class.java)
}