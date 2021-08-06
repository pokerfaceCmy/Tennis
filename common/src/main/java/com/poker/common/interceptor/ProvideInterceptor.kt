package com.poker.common.interceptor

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.blankj.utilcode.util.AppUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/30 14:51
 * @Desc: di
 * @GitHub：https://github.com/pokerfaceCmy
 */

@Module
@InstallIn(SingletonComponent::class)
object LocalData {
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
        return applicationContext.createDataStore(
            name = AppUtils.getAppPackageName()
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object Interceptor {
    @Provides
    @Singleton
    fun provideTokenInterceptor(dataStore: DataStore<Preferences>): TokenInterceptor {
        return TokenInterceptor(dataStore)
    }
}

