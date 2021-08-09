package com.wetech.aus.tennis.app.domain.club

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.alibaba.android.arouter.facade.annotation.Route
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityClubBookingBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.booking.repository.bean.DaysResponse
import com.wetech.aus.tennis.app.domain.booking.vm.BookingViewModel
import com.wetech.aus.tennis.app.domain.club.adapter.DateAdapter
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Club.ClubBookingActivity)
@AndroidEntryPoint
class ClubBookingActivity : BaseActivity<ActivityClubBookingBinding>() {
    private val viewModel by getViewModel(BookingViewModel::class.java) {
        getDaysLD.observe(mLifecycleOwner, {
            dateAdapter.setList(it?.list)
        })
    }
    private val dateAdapter by lazy {
        DateAdapter()
    }

    override fun init() {
        viewModel.getDays("", 14)

        binding.apply {
            toolBar.btnBack.setOnClickListener { finish() }
            toolBar.tvTitle.text = getString(R.string.court_booking)

            rvDate.layoutManager = LinearLayoutManager(mContext, HORIZONTAL, false)
            rvDate.adapter = dateAdapter
            dateAdapter.setOnItemClickListener { adapter, view, position ->
                cardView2.visibility = View.VISIBLE
                val data = adapter.data as List<DaysResponse.Data>
                data.map {
                    it.isCheck = false
                }

                data[position].isCheck = true

                dateAdapter.setList(data)
            }
        }
    }
}