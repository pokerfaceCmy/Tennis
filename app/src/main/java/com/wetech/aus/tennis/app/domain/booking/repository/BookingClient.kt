package com.wetech.aus.tennis.app.domain.booking.repository

import com.wetech.aus.tennis.app.bean.ApiService
import com.wetech.aus.tennis.app.domain.booking.repository.bean.BookingRequest
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 14:58
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class BookingClient @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun queryBookingList(bookingRequest : BookingRequest) = apiService.queryBookingList(bookingRequest)
}