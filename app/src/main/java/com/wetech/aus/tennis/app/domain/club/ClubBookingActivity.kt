package com.wetech.aus.tennis.app.domain.club

import com.alibaba.android.arouter.facade.annotation.Route
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityClubBookingBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Companion.Club.ClubBookingActivity)
@AndroidEntryPoint
class ClubBookingActivity : BaseActivity<ActivityClubBookingBinding>() {

    override fun init() {
        binding.apply {
            toolBar.btnBack.setOnClickListener { finish() }
            toolBar.tvTitle.text = getString(R.string.court_booking)
        }
    }
}