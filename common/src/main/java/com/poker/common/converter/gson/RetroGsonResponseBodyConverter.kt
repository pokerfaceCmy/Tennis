package com.poker.common.converter.gson

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.poker.common.bean.IApiResult
import com.poker.common.exception.ServerException
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/9 11:10
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RetroGsonResponseBodyConverter<T, ApiResultType : IApiResult<*>> constructor(
    private val gson: Gson,
    private val apiClass: Class<ApiResultType>,
    private val type: Type
) : Converter<ResponseBody, T> {

    @Suppress("UNCHECKED_CAST")
    override fun convert(value: ResponseBody): T? {
        value.use {
            val response = it.string()

            val apiResult = gson.fromJson(response, apiClass)
            if (!apiResult.isSuccess) throw ServerException(apiResult.httpCode, apiResult.httpMsg)
            if (type == apiClass) return apiResult as T
            if (apiResult.dataField.isEmpty()) return gson.fromJson(response, type)
            if (apiResult.httpData == null) return apiResult as T
            return gson.fromJson(
                (JsonParser().parse(response) as JsonObject).get(apiResult.dataField),
                type
            )
        }
    }

}