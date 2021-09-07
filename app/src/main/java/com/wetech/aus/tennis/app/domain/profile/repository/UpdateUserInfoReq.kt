package com.wetech.aus.tennis.app.domain.profile.repository

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/9/6 10:27
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class UpdateUserInfoReq(
    val age : Int?,
    val birthday : String?,
    val email:String?,
    val firstName : String?,
    val gender : String?,
    val lastName : String?,
)
