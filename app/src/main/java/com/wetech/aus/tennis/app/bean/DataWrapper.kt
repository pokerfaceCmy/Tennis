package com.wetech.aus.tennis.app.bean

import com.poker.common.bean.IApiResult

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/29 9:52
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class DataWrapper<Data>(
    val code: Int,
    val msg: String,
    val data: Data?,
    val extraMsg: String
) : IApiResult<Data> {
    override val isSuccess: Boolean
        get() = code == 2000
    override val httpData: Data?
        get() = data
    override val httpMsg: String
        get() = msg
    override val httpCode: Int
        get() = code
    override val dataField: String
        get() = "data"
}