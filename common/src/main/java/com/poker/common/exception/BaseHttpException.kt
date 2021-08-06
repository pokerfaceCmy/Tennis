package com.poker.common.exception

import java.io.IOException

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/14 11:43
 * @Desc: 错误处理
 * @GitHub：https://github.com/pokerfaceCmy
 */
open class BaseHttpException constructor(
    val errCode: Int,
    val errMsg: String,
) : IOException(errMsg)