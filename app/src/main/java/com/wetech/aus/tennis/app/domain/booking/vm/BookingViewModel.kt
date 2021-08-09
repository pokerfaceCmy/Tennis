package com.wetech.aus.tennis.app.domain.booking.vm

import androidx.lifecycle.MutableLiveData
import com.poker.common.base.BaseViewModel
import com.wetech.aus.tennis.app.domain.booking.repository.BookingClient
import com.wetech.aus.tennis.app.domain.booking.repository.bean.BookingListResponse
import com.wetech.aus.tennis.app.domain.booking.repository.bean.BookingRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 14:56
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltViewModel
class BookingViewModel @Inject constructor(
    private val bookingClient: BookingClient
) : BaseViewModel() {
    val queryBookingListLD = MutableLiveData<BookingListResponse?>()

    fun queryBookingList(bookingRequest: BookingRequest) {
        enqueue({ bookingClient.queryBookingList(bookingRequest) }) {
            onSuccess {
                queryBookingListLD.value = it
            }
        }
    }
}