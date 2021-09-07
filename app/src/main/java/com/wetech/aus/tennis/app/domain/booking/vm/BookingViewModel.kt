package com.wetech.aus.tennis.app.domain.booking.vm

import androidx.lifecycle.MutableLiveData
import com.poker.common.base.BaseViewModel
import com.wetech.aus.tennis.app.bean.DataWrapper
import com.wetech.aus.tennis.app.domain.booking.repository.BookingClient
import com.wetech.aus.tennis.app.domain.booking.repository.bean.*
import com.wetech.aus.tennis.app.domain.club.FindVipResponse
import com.wetech.aus.tennis.app.domain.home.repository.HomeClient
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
    private val bookingClient: BookingClient,
    private val homeClient: HomeClient
) : BaseViewModel() {
    val queryBookingListLD = MutableLiveData<BookingListResponse?>()
    val getDaysLD = MutableLiveData<DaysResponse?>()
    val getUsablePlaceTimeLD = MutableLiveData<UsablePlaceTimeResponse?>()
    val getUsablePlaceByDayLD = MutableLiveData<UsablePlaceByDayResponse?>()
    val getTokenLD = MutableLiveData<PayTokenResponse?>()
    val payPlayLD = MutableLiveData<PayPlayResponse?>()
    val saveOrderLD = MutableLiveData<OrderResponse?>()
    val findVipLD = MutableLiveData<FindVipResponse?>()
    val likeClubLD = MutableLiveData<DataWrapper<*>?>()


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


    fun findVip(id: Long) {
        enqueue({bookingClient.findVip(id)}){
            onSuccess {
                findVipLD.value = it
            }
        }
    }

    fun likeClub(clubId: Long?, type: String){
        enqueue(
            { clubId?.let { homeClient.likeClub(it, type) } },
            showLoading = false,
            showErrorMsg = false
        ) {
            onSuccess {
                likeClubLD.value = it
            }
        }
    }
}