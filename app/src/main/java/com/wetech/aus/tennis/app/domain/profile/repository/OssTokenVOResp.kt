package com.wetech.aus.tennis.app.domain.profile.repository

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/31 15:38
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class OssTokenVOResp(
    val accessKeyId: String?,
    val accessKeySecret: String?,
    val bucket: String?,
    val expiration: String?,
    val region: String?,
    val securityToken: String?,
    var imgPath : String
)