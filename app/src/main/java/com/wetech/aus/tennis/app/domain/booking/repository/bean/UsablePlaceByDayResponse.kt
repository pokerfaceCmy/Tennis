package com.wetech.aus.tennis.app.domain.booking.repository.bean

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/10 11:38
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class UsablePlaceByDayResponse(
    val list: List<Data>
) {
    data class Data(
        val clubId: Long?,
        val cover: String?,
        val id: Long?,
        val intro: String?,
        val name: String?,
        val status: String?,
    )
}
