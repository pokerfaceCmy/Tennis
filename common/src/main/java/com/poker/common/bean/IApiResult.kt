package com.poker.common.bean

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/13 17:33
 * @Desc: 网络请求的实体类接口
 * @GitHub：https://github.com/pokerfaceCmy
 */
interface IApiResult<Data> {
    /**
     * 是否请求成功
     */
    val isSuccess: Boolean

    /**
     * 返回的数据
     */
    val httpData: Data?

    /**
     * 返回的msg
     */
    val httpMsg: String

    /**
     * 返回的错误码
     */
    val httpCode: Int

    /**
     * data字段的Json名
     */
    val dataField: String
}