package com.poker.common.interceptor

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/16 10:08
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Logger.json(request.body.toString())
        val response = chain.proceed(request)
        Logger.json(response.body.toString())
        return response
    }
}