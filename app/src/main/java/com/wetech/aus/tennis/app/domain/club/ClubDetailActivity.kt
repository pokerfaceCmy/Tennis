package com.wetech.aus.tennis.app.domain.club

import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.leaf.library.StatusBarUtil
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivityClubDetailBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.club.adapter.CoachAdapter
import com.wetech.aus.tennis.app.domain.club.adapter.FacilitiesAdapter
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Club.ClubDetailActivity)
@AndroidEntryPoint
class ClubDetailActivity : BaseActivity<ActivityClubDetailBinding>() {
    companion object {
        const val CLUB_DETAIL = "clubDetail"
    }

    private val facilitiesAdapter by lazy {
        FacilitiesAdapter()
    }

    private val coachAdapter by lazy {
        CoachAdapter()
    }

    @Autowired
    lateinit var clubDetail: ClubListResponse.Data

    override fun init() {
        StatusBarUtil.setTransparentForWindow(this)
        binding.apply {
            ivHead.load(clubDetail.cover)
            rvFacilities.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            rvFacilities.adapter = facilitiesAdapter

            facilitiesAdapter.setList(mutableListOf("", "", "", "", "", "", ""))

            rvCoach.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            rvCoach.adapter = coachAdapter

            coachAdapter.setList(mutableListOf("", "", "", "", "", "", ""))


            tvClubName.text = clubDetail.name

            tvIntroduction.text = clubDetail.clubDesc

            clBooking.setOnClickListener {
                ARouter.getInstance()
                    .build(RoutePath.Club.ClubBookingActivity)
                    .navigation()
            }

            tvMembership.setOnClickListener {
                ARouter.getInstance()
                    .build("")
                    .navigation()
            }

        }
    }
}