package com.poker.common.converter.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.poker.common.bean.IApiResult
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/9 9:01
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RetroGsonConverterFactory<ApiResultType : IApiResult<*>>
private constructor(
    private val gson: Gson,
    private val apiClass: Class<ApiResultType>
) : Converter.Factory() {

    companion object {
        fun <ApiResultType : IApiResult<*>> create(
            gson: Gson,
            apiClass: Class<ApiResultType>
        ): RetroGsonConverterFactory<ApiResultType> {
            return RetroGsonConverterFactory(gson, apiClass)
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return RetroGsonResponseBodyConverter<Any, ApiResultType>(gson, apiClass, type)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        return RetroGsonRequestBodyConverter(gson, gson.getAdapter(TypeToken.get(type)))
    }
}