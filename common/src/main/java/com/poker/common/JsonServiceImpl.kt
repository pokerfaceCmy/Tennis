package com.poker.common

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/2 15:32
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
@Route(path = "/JsonServiceImpl/json")
class JsonServiceImpl : SerializationService {
    private lateinit var gson: Gson

    override fun init(context: Context?) {
        gson = Gson()
    }

    override fun <T : Any?> json2Object(input: String?, clazz: Class<T>?): T {
        return gson.fromJson(input, clazz)
    }

    override fun object2Json(instance: Any?): String {
        return gson.toJson(instance)
    }

    override fun <T : Any?> parseObject(input: String?, clazz: Type?): T {
        return gson.fromJson(input, clazz)
    }

}