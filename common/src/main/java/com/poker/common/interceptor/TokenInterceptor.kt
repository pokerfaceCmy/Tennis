package com.poker.common.interceptor

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/30 14:08
 * @Desc: token拦截器
 * @GitHub：https://github.com/pokerfaceCmy
 */
class TokenInterceptor @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : Interceptor {
    companion object {
        const val TOKEN = "token"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val data = runBlocking { dataStore.data.first() }
        val token = data[preferencesKey<String>(name = TOKEN)] ?: ""
        val requestBuilder = original.newBuilder()
            .addHeader(
                "Authorization",
                token
            )
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}