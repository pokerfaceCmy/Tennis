package com.wetech.aus.tennis.app.domain.booking.vm

import androidx.lifecycle.MutableLiveData
import com.poker.common.base.BaseViewModel
import com.wetech.aus.tennis.app.domain.booking.repository.BookingClient
import com.wetech.aus.tennis.app.domain.booking.repository.bean.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/9 14:56
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltViewModel
class BookingViewModel @Inject constructor(
    private val bookingClient: BookingClient
) : BaseViewModel() {
    val queryBookingListLD = MutableLiveData<BookingListResponse?>()
    val getDaysLD = MutableLiveData<DaysResponse?>()
    val getUsablePlaceTimeLD = MutableLiveData<UsablePlaceTimeResponse?>()
    val getUsablePlaceByDayLD = MutableLiveData<UsablePlaceByDayResponse?>()
    val getTokenLD = MutableLiveData<PayTokenResponse?>()
    val payPlayLD = MutableLiveData<PayPlayResponse?>()
    val saveOrderLD = MutableLiveData<OrderResponse?>()

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

    fun getUsablePlaceTime(day: String) {
        enqueue({ bookingClient.getUsablePlaceTime(day) }) {
            onSuccess {
                getUsablePlaceTimeLD.value = it
            }
        }
    }

    fun getUsablePlaceByDay(
        day: String,
        startSlot: String,
        endSlot: String,
        clubId: Long
    ) {
        enqueue({ bookingClient.getUsablePlaceByDay(day, startSlot, endSlot, clubId) }) {
            onSuccess {
                getUsablePlaceByDayLD.value = it
            }
        }
    }

    fun saveOrder(saveOrderRequest: SaveOrderRequest) {
        enqueue({ bookingClient.saveOrder(saveOrderRequest) }) {
            onSuccess {
                saveOrderLD.value = it
            }
        }
    }


    fun getToken() {
        enqueue({ bookingClient.getToken() }) {
            onSuccess {
                getTokenLD.value = it
            }
        }
    }

    fun payPlay(
        nonce: String,
        orderId: Long,
    ) {
        enqueue({ bookingClient.payPlay(nonce, orderId) }) {
            onSuccess {
                payPlayLD.value = it
            }
        }
    }
}