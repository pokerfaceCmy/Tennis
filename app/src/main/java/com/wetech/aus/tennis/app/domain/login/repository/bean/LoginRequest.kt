package com.wetech.aus.tennis.app.domain.login.repository.bean

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/30 10:06
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class LoginRequest(
    //1，密码登录，2验证码登录
    val loginType: Int = 2,
    //手机号
    val phone: String,
    //手机号前缀
    val phonePrefix: String = "",
    //验证码
    val code: String = "",
    //密码
    val password: String = "",
    // 用户名
    val username: String = phonePrefix + phone
)
