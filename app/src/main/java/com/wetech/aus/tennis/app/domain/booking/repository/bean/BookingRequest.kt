package com.wetech.aus.tennis.app.domain.booking.repository.bean

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 15:03
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
data class BookingRequest(
    val pageNum: Int,
    val pageSize: Int = 10,
)
