package com.wetech.aus.tennis.app.domain.login.repository.remote

import com.wetech.aus.tennis.app.bean.ApiService
import com.wetech.aus.tennis.app.domain.login.repository.bean.LoginRequest
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 9:58
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class LoginClient @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun sendSms(phone: String, prefix: String) = apiService.sendSms(phone, prefix)

    suspend fun getCountryMobilePrefix() = apiService.getCountryMobilePrefix()

    suspend fun checkHasUser(phone: String, prefix: String) = apiService.checkHasUser(phone, prefix)

    suspend fun login(loginRequest: LoginRequest) = apiService.login(loginRequest)
}