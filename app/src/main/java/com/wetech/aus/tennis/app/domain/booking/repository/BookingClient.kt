package com.wetech.aus.tennis.app.domain.booking.repository

import com.wetech.aus.tennis.app.bean.ApiService
import com.wetech.aus.tennis.app.domain.booking.repository.bean.BookingRequest
import com.wetech.aus.tennis.app.domain.booking.repository.bean.SaveOrderRequest
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 14:58
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
class BookingClient @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun queryBookingList(bookingRequest: BookingRequest) =
        apiService.queryBookingList(bookingRequest)

    suspend fun getDays(day: String, intervals: Int) = apiService.getDays(day, intervals)

    suspend fun getUsablePlaceTime(day: String) = apiService.getUsablePlaceTime(day)

    suspend fun getUsablePlaceByDay(
        day: String,
        startSlot: String,
        endSlot: String,
        clubId: Long
    ) = apiService.getUsablePlaceByDay(day, startSlot, endSlot, clubId)

    suspend fun saveOrder(saveOrderRequest: SaveOrderRequest) = apiService.saveOrder(saveOrderRequest)

    suspend fun getToken() = apiService.getToken()

    suspend fun payPlay(nonce: String,
                        orderId: Long,) = apiService.payPlay(nonce, orderId)

    suspend fun findVip(id:Long) = apiService.findVip(id)
}