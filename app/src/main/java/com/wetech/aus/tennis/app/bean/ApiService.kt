package com.wetech.aus.tennis.app.bean

import com.wetech.aus.tennis.app.domain.login.repository.bean.PrefixResponse
import retrofit2.http.GET

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 9:58
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
interface ApiService {
    /**
     * 发送验证码
     */
    @GET("/system/sendSms")
    suspend fun sendSms(): DataWrapper<Any>

    /**
     * 获取国家对应的区号
     */
    @GET("/system/getCountryMobilePrefix")
    suspend fun getCountryMobilePrefix(): PrefixResponse?
}