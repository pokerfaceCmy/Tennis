package com.wetech.aus.tennis.app.domain.club

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityClubBookingBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.booking.repository.bean.DaysResponse
import com.wetech.aus.tennis.app.domain.booking.repository.bean.UsablePlaceTimeResponse
import com.wetech.aus.tennis.app.domain.booking.vm.BookingViewModel
import com.wetech.aus.tennis.app.domain.club.adapter.DateAdapter
import com.wetech.aus.tennis.app.domain.club.adapter.TimeAdapter
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Club.ClubBookingActivity)
@AndroidEntryPoint
class ClubBookingActivity : BaseActivity<ActivityClubBookingBinding>() {
    private val viewModel by getViewModel(BookingViewModel::class.java) {
        getDaysLD.observe(mLifecycleOwner, {
            dateAdapter.setList(it?.list)
        })

        getUsablePlaceByDayLD.observe(mLifecycleOwner, {
            timeAdapter.setList(it?.list)
        })
    }

    @Autowired
    lateinit var clubDetail: ClubListResponse.Data

    private val dateAdapter by lazy {
        DateAdapter()
    }

    private val timeAdapter by lazy {
        TimeAdapter()
    }


    @Suppress("UNCHECKED_CAST")
    override fun init() {
        viewModel.getDays("", 14)

        binding.apply {
            toolBar.btnBack.setOnClickListener { finish() }
            toolBar.tvTitle.text = getString(R.string.court_booking)

            rvDate.layoutManager = LinearLayoutManager(mContext, HORIZONTAL, false)
            rvDate.adapter = dateAdapter

            rvTime.layoutManager = LinearLayoutManager(mContext, HORIZONTAL, false)
            rvTime.adapter = timeAdapter

            dateAdapter.setOnItemClickListener { adapter, _, position ->
                cardView2.visibility = View.VISIBLE
                val data = adapter.data as List<DaysResponse.Data>
                data.map {
                    it.isCheck = false
                }
                data[position].isCheck = true
                dateAdapter.setList(data)

                viewModel.getUsablePlaceByDay(day = data[position].date.toString())
            }

            timeAdapter.setOnItemClickListener { adapter, view, position ->
                textView2.visibility = View.VISIBLE
                rvPlace.visibility = View.VISIBLE

                val data = adapter.data as List<UsablePlaceTimeResponse.Data>
                data.map {
                    it.isCheck = false
                }
                data[position].isCheck = true
                timeAdapter.setList(data)
                // TODO: 2021/8/9 viewModel.获取可用场地
            }


        }
    }
}