package com.wetech.aus.tennis.app.domain.club

import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.leaf.library.StatusBarUtil
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivityClubDetailBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.club.adapter.CoachAdapter
import com.wetech.aus.tennis.app.domain.club.adapter.FacilitiesAdapter
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Companion.Club.ClubDetailActivity)
@AndroidEntryPoint
class ClubDetailActivity : BaseActivity<ActivityClubDetailBinding>() {
    private val facilitiesAdapter by lazy {
        FacilitiesAdapter()
    }

    private val coachAdapter by lazy {
        CoachAdapter()
    }

    override fun init() {
        StatusBarUtil.setTransparentForWindow(this)
        binding.apply {
            ivHead.load("https://media-exp3.licdn.com/dms/image/C561BAQGINDn2oxh9yA/company-background_10000/0/1548172515883?e=2159024400&v=beta&t=kvMBvPa9eLveFml9cqL4evLWc7to5-lZfevaCp9PmnM")
            rvFacilities.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            rvFacilities.adapter = facilitiesAdapter

            facilitiesAdapter.setList(mutableListOf("", "", "", "", "", "", ""))

            rvCoach.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            rvCoach.adapter = coachAdapter

            coachAdapter.setList(mutableListOf("", "", "", "", "", "", ""))


            tvClubName.text = "Tennis Club Name"
            tvIntroduction.text =
                "Club Introduction：坐落在巴黎西郊的罗兰·加洛斯网球场是法国网球公开赛的比赛场地，作为四大网球开赛中唯一采用红土场地的赛事，球场以法国民族英雄罗兰·加洛斯命名。每年5月底至6月初都会在这个"

            clBooking.setOnClickListener {
                ARouter.getInstance()
                    .build(RoutePath.Companion.Club.ClubBookingActivity)
                    .navigation()
            }

            tvMembership.setOnClickListener {
                // TODO: 2021/7/5  购买会员
                ARouter.getInstance()
                    .build("")
                    .navigation()
            }
        }
    }
}