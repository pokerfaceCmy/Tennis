package com.poker.common.exception

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/16 11:04
 * @Desc: 错误处理
 * @GitHub：https://github.com/pokerfaceCmy
 */
class ServerException(errCode: Int, errMsg: String) : BaseHttpException(errCode, errMsg)