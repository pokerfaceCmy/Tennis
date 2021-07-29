package com.wetech.aus.tennis.app.di

import com.google.gson.Gson
import com.poker.common.converter.gson.RetroGsonConverterFactory
import com.wetech.aus.tennis.app.BuildConfig
import com.wetech.aus.tennis.app.bean.ApiService
import com.wetech.aus.tennis.app.bean.DataWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 9:49
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */

//过期时间 单位秒
private const val TIMEOUT = 10L

private const val BASE_URL_DEBUG = "http://159.138.93.235:8080"

private const val BASE_URL = "http://159.138.93.235:8080"

@Module
@InstallIn(SingletonComponent::class)
object JsonModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(logInterceptor)
            .retryOnConnectionFailure(true)
            .hostnameVerifier { _, _ -> true }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(if (BuildConfig.DEBUG) BASE_URL_DEBUG else BASE_URL)
            .addConverterFactory(RetroGsonConverterFactory.create(gson, DataWrapper::class.java))
            .build()
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}