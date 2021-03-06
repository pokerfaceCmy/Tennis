package com.wetech.aus.tennis.app.domain.login.repository.bean

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 11:10
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */

data class PrefixResponse(
    val list: List<Data>?
) {
    data class Data(
        val area: String?,
        val code: String?,
        val id: Int?,
        val name: String?
    )
}

