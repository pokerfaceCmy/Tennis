package com.wetech.aus.tennis.app.domain.booking.repository.bean

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 16:20
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class DaysResponse(
    val list: List<Data>?
) {
    data class Data(
        val day: Int?,
        val week: String?,
        var isCheck: Boolean = false
    )
}
