package com.poker.common.exception

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/16 11:04
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class ServerException(errCode: Int, errMsg: String) : BaseHttpException(errCode, errMsg)