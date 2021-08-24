package com.wetech.aus.tennis.app.domain.booking.repository.bean

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/24 16:29
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */

/**
 * @param clubId 俱乐部Id
 * @param orderDate 预定日期（2021-07-08）
 * @param startSlot 预定开始时间段
 * @param endSlot 预定结束时间段
 * @param placeId 场地Id
 */
data class SaveOrderRequest(
    val clubId: Long,
    val orderDate: String,
    val startSlot: String,
    val endSlot: String,
    val placeId: Long
)

/**
 * @param 订单Id
 */
data class OrderResponse(
    val orderId: Long?
)