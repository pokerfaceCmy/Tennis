package com.pcyun.common.exception

import java.io.IOException

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/4/14 11:43
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
open class BaseHttpException constructor(
    val errCode: Int,
    val errMsg: String,
) : IOException(errMsg)