package com.wetech.aus.tennis.app.domain.login.repository.remote

import com.wetech.aus.tennis.app.bean.ApiService
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 9:58
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class LoginClient @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun sendSms() = apiService.sendSms()
    suspend fun getCountryMobilePrefix() = apiService.getCountryMobilePrefix()
}