package com.wetech.aus.tennis.app.domain.club

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.leaf.library.StatusBarUtil
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityClubDetailBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.booking.vm.BookingViewModel
import com.wetech.aus.tennis.app.domain.club.adapter.CoachAdapter
import com.wetech.aus.tennis.app.domain.club.adapter.FacilitiesAdapter
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListResponse
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Club.ClubDetailActivity)
@AndroidEntryPoint
class ClubDetailActivity : BaseActivity<ActivityClubDetailBinding>() {
    private val viewModel by getViewModel(BookingViewModel::class.java) {
        findVipLD.observe(mLifecycleOwner, {
            if (it?.flag == true) {
                binding.clVisitorEnter.visibility = View.GONE
            } else {
                binding.clBooking.visibility = View.GONE
            }
        })


    }

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
        viewModel.findVip(clubDetail.id ?: 0)
        StatusBarUtil.setTransparentForWindow(this)
        binding.apply {
            ivHead.load(clubDetail.cover)
            ivLike.load(if (clubDetail.enjoy == 1) R.drawable.ic_favorites_h else R.drawable.ic_favorites_n)

            ivLike.setOnClickListener {
                var nowEnjoy = clubDetail.enjoy
                viewModel.likeClub(clubDetail.id, if (clubDetail.enjoy == 1) "2" else "1")
                viewModel.likeClubLD.observe(mLifecycleOwner, {
                    nowEnjoy = if (nowEnjoy == 1) 2 else 1
                    ivLike.load(if (nowEnjoy == 1) R.drawable.ic_favorites_h else R.drawable.ic_favorites_n)
                })
            }

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
                    .withObject(CLUB_DETAIL, clubDetail)
                    .withString("isVip", "1")
                    .navigation()
            }
            clVisitorEnter.setOnClickListener {
                ARouter.getInstance()
                    .build(RoutePath.Club.ClubBookingActivity)
                    .withObject(CLUB_DETAIL, clubDetail)
                    .withString("isVip", "0")
                    .navigation()
            }

            clMembership.setOnClickListener {
//                ARouter.getInstance()
//                    .build("")
//                    .navigation()
            }
        }
    }
}