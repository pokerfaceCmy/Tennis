package com.wetech.aus.tennis.app.domain.club

import android.content.Intent
import android.os.Handler
import android.os.SystemClock.sleep
import android.view.View.VISIBLE
import android.widget.ScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.braintreepayments.api.dropin.DropInActivity
import com.braintreepayments.api.dropin.DropInRequest
import com.braintreepayments.api.dropin.DropInResult
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityClubBookingBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.booking.repository.bean.DaysResponse
import com.wetech.aus.tennis.app.domain.booking.repository.bean.SaveOrderRequest
import com.wetech.aus.tennis.app.domain.booking.repository.bean.UsablePlaceByDayResponse
import com.wetech.aus.tennis.app.domain.booking.repository.bean.UsablePlaceTimeResponse
import com.wetech.aus.tennis.app.domain.booking.vm.BookingViewModel
import com.wetech.aus.tennis.app.domain.club.adapter.DateAdapter
import com.wetech.aus.tennis.app.domain.club.adapter.PlaceAdapter
import com.wetech.aus.tennis.app.domain.club.adapter.TimeAdapter
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@Route(path = RoutePath.Club.ClubBookingActivity)
@AndroidEntryPoint
class ClubBookingActivity : BaseActivity<ActivityClubBookingBinding>() {
    private val viewModel by getViewModel(BookingViewModel::class.java) {
        getDaysLD.observe(mLifecycleOwner, {
            dateAdapter.setList(it?.list)
        })

        getUsablePlaceTimeLD.observe(mLifecycleOwner, {
            timeAdapter.setList(it?.list)
        })

        getUsablePlaceByDayLD.observe(mLifecycleOwner, {
            placeAdapter.setList(it?.list)
            Handler().post { binding.nestedScrollView.fullScroll(ScrollView.FOCUS_DOWN) }
        })

        saveOrderLD.observe(mLifecycleOwner, {
            orderId = it?.orderId ?: 0L
            if (isVip == "1") {
                ToastUtils.showLong("预定成功")
            } else {
                getToken()
            }
        })

        getTokenLD.observe(mLifecycleOwner, {
            val dropInRequest = DropInRequest().tokenizationKey(it?.payToken)
            startActivityForResult(dropInRequest.getIntent(this@ClubBookingActivity), 200)
        })

        payPlayLD.observe(mLifecycleOwner, {
            showToast(if (it?.payFlag == true) "预定成功" else "预定失败")
        })
    }

    @Autowired
    lateinit var clubDetail: ClubListResponse.Data

    @Autowired
    lateinit var isVip: String

    private var orderDate: String = ""
    private var startSlot: String = ""
    private var endSlot: String = ""
    private var placeId: Long = 0L
    private var orderId: Long = 0L

    private val dateAdapter by lazy {
        DateAdapter()
    }

    private val timeAdapter by lazy {
        TimeAdapter()
    }

    private val placeAdapter by lazy {
        PlaceAdapter()
    }

    @Suppress("UNCHECKED_CAST")
    override fun init() {
        viewModel.getDays("", 14)

        binding.apply {
            toolBar.btnBack.setOnClickListener { finish() }
            toolBar.tvTitle.text = getString(R.string.court_booking)

            rvDate.layoutManager = LinearLayoutManager(mContext, HORIZONTAL, false)
            rvDate.adapter = dateAdapter

            rvTime.layoutManager = LinearLayoutManager(mContext, VERTICAL, false)
            rvTime.adapter = timeAdapter

            rvPlace.layoutManager = LinearLayoutManager(mContext, HORIZONTAL, false)
            rvPlace.adapter = placeAdapter

            dateAdapter.setOnItemClickListener { adapter, _, position ->
                val placeData = placeAdapter.data as List<UsablePlaceByDayResponse.Data>
                placeData.map {
                    it.isCheck = false
                }
                placeAdapter.setList(placeData)

                cardView2.visibility = VISIBLE
                val data = adapter.data as List<DaysResponse.Data>
                orderDate = data[position].date ?: ""

                data.map {
                    it.isCheck = false
                }
                data[position].isCheck = true
                dateAdapter.setList(data)

                viewModel.getUsablePlaceTime(day = data[position].date.toString())
            }

            timeAdapter.setOnItemClickListener { adapter, _, position ->
                textView2.visibility = VISIBLE
                rvPlace.visibility = VISIBLE

                val data = adapter.data as List<UsablePlaceTimeResponse.Data>

                startSlot = data[position].startSlot ?: ""
                endSlot = data[position].endSlot ?: ""

                data.map {
                    it.isCheck = false
                }
                data[position].isCheck = true
                timeAdapter.setList(data)

                val req = data[position]

                viewModel.getUsablePlaceByDay(
                    req.time ?: "",
                    req.startSlot ?: "",
                    req.endSlot ?: "",
                    clubDetail.id ?: 0
                )
            }

            placeAdapter.setOnItemClickListener { adapter, _, position ->
                val data = adapter.data as List<UsablePlaceByDayResponse.Data>
                placeId = data[position].id ?: 0L
                data.map {
                    it.isCheck = false
                }
                data[position].isCheck = true
                placeAdapter.setList(data)

                btnBooking.visibility = VISIBLE

                Handler().post { binding.nestedScrollView.fullScroll(ScrollView.FOCUS_DOWN) }

            }

            btnBooking.setOnClickListener {
                viewModel.saveOrder(
                    SaveOrderRequest(
                        clubId = clubDetail.id ?: 0L,
                        orderDate = orderDate,
                        startSlot = startSlot,
                        endSlot = endSlot,
                        placeId = placeId,
                    )
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            when (resultCode) {
                RESULT_OK -> {
                    val result =
                        data?.getParcelableExtra<DropInResult>(DropInResult.EXTRA_DROP_IN_RESULT)

                    viewModel.payPlay(result?.paymentMethodNonce?.nonce ?: "", orderId)
                }
                RESULT_CANCELED -> {
                    Timber.d("已取消")
                }
                else -> {
                    val error = data?.getSerializableExtra(DropInActivity.EXTRA_ERROR) as Exception
                    error.printStackTrace()
                }
            }
        }
    }
}