package com.wetech.aus.tennis.app.domain.booking.repository.bean

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 17:37
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class UsablePlaceTimeResponse(
    val list: List<Data>?
) {
    data class Data(
        val desc: Int?,
        val endSlot: String?,
        val startSlot: String?,
        val status: String?,
        val time: String?,
        var isCheck: Boolean = false
    )
}
