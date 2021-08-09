package com.wetech.aus.tennis.app.domain.booking.vm

import androidx.lifecycle.MutableLiveData
import com.poker.common.base.BaseViewModel
import com.wetech.aus.tennis.app.domain.booking.repository.BookingClient
import com.wetech.aus.tennis.app.domain.booking.repository.bean.BookingListResponse
import com.wetech.aus.tennis.app.domain.booking.repository.bean.BookingRequest
import com.wetech.aus.tennis.app.domain.booking.repository.bean.DaysResponse
import com.wetech.aus.tennis.app.domain.booking.repository.bean.UsablePlaceTimeResponse
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
    val getDaysLD = MutableLiveData<DaysResponse?>()
    val getUsablePlaceByDayLD = MutableLiveData<UsablePlaceTimeResponse?>()

    fun queryBookingList(bookingRequest: BookingRequest) {
        enqueue({ bookingClient.queryBookingList(bookingRequest) }) {
            onSuccess {
                queryBookingListLD.value = it
            }
        }
    }

    fun getDays(day: String, intervals: Int) {
        enqueue({ bookingClient.getDays(day, intervals) }) {
            onSuccess {
                getDaysLD.value = it
            }
        }
    }

    fun getUsablePlaceByDay(day: String) {
        enqueue({ bookingClient.getUsablePlaceTime(day) }) {
            onSuccess {
                getUsablePlaceByDayLD.value = it
            }
        }
    }
}