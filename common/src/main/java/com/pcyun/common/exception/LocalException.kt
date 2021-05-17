package com.pcyun.common.exception

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/26 14:35
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class LocalException(errCode: Int, errMsg: String) : BaseHttpException(errCode, errMsg)