package com.wetech.aus.tennis.app.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.poker.common.converter.gson.RetroGsonConverterFactory
import com.poker.common.interceptor.TokenInterceptor
import com.wetech.aus.tennis.app.BuildConfig
import com.wetech.aus.tennis.app.app.AppDatabase
import com.wetech.aus.tennis.app.bean.ApiService
import com.wetech.aus.tennis.app.bean.DataWrapper
import com.wetech.aus.tennis.app.di.ProjectConfig.BASE_URL
import com.wetech.aus.tennis.app.di.ProjectConfig.BASE_URL_DEBUG
import com.wetech.aus.tennis.app.di.ProjectConfig.DATABASE_NAME
import com.wetech.aus.tennis.app.di.ProjectConfig.TIMEOUT
import com.wetech.aus.tennis.app.domain.login.repository.bean.UserInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 9:49
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */


object ProjectConfig {
    //过期时间 单位秒
    const val TIMEOUT = 10L
    const val BASE_URL_DEBUG = "http://159.138.93.235:8080"
    const val BASE_URL = "http://159.138.93.235:8080" // FIXME: 2021/7/30 等待正式Url地址给出后修改
    const val DATABASE_NAME = "Tennis.db"
}

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
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(logInterceptor)
            .addInterceptor(tokenInterceptor)
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
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideAppDataBase(
        @ApplicationContext applicationContext: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserInfoDao(appDatabase: AppDatabase): UserInfoDao {
        return appDatabase.userInfoDao()
    }
}
