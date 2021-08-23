package com.wetech.aus.tennis.app.domain.home

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import coil.load
import com.alibaba.android.arouter.launcher.ARouter
import com.poker.common.base.BaseFragment
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentHomeBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.club.ClubDetailActivity.Companion.CLUB_DETAIL
import com.wetech.aus.tennis.app.domain.home.adapter.FavouriteAdapter
import com.wetech.aus.tennis.app.domain.home.adapter.RecommendAdapter
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListRequest
import com.wetech.aus.tennis.app.domain.home.vm.HomeViewModel
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:09
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var mViewPager: BannerViewPager<String>
    private val recommendAdapter by lazy {
        RecommendAdapter()
    }
    private val favouriteAdapter by lazy {
        FavouriteAdapter()
    }

    private val viewModel by getViewModel(HomeViewModel::class.java) {
        bannerLD.observe(mLifecycleOwner, {
            mViewPager.refreshData(
                it?.list?.map { data -> data.imageUrl }
            )
        })

        queryRecommendClubListLD.observe(mLifecycleOwner, {
            recommendAdapter.setList(it?.list)
        })
        queryFavouritesClubListLD.observe(mLifecycleOwner, {
            favouriteAdapter.setList(it?.list)
        })
    }

    override fun init() {
        viewModel.getBanner()

        viewModel.queryRecommendClubList(
            ClubListRequest(
                pageNum = 1
            )
        )
        viewModel.queryFavouritesClubList(
            ClubListRequest(
                pageNum = 1,
                enjoy = 1
            )
        )

        binding.apply {
            mViewPager = root.findViewById(R.id.banner)

            mViewPager.apply {
                adapter = BannerAdapter()
                setLifecycleRegistry(lifecycle)
            }.create()

            rvRecommend.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            rvRecommend.adapter = recommendAdapter
            val pagerSnapHelper = PagerSnapHelper()
            pagerSnapHelper.attachToRecyclerView(rvRecommend)

            recommendAdapter.addChildClickViewIds(R.id.ivLike)
            recommendAdapter.setOnItemClickListener { adapter, _, position ->
                ARouter.getInstance()
                    .build(RoutePath.Club.ClubDetailActivity)
                    .withObject(CLUB_DETAIL, adapter.data[position])
                    .navigation()
            }
            recommendAdapter.setOnItemChildClickListener { _, view, position ->
                when (view.id) {
                    R.id.ivLike -> {
                        val data = recommendAdapter.data[position]
                        val newData = data?.copy(
                            enjoy = if (data.enjoy == 1) 2 else 1
                        )
                        val enjoy = data?.enjoy
                        viewModel.likeClub(data?.id, if (enjoy == 1) "2" else "1")

                        viewModel.likeClubLD.observe(mLifecycleOwner, {
                            recommendAdapter.setData(position, newData)
                        })
                    }
                }
            }

            rvFavourites.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            rvFavourites.adapter = favouriteAdapter
            val pagerSnapHelper1 = PagerSnapHelper()
            pagerSnapHelper1.attachToRecyclerView(rvFavourites)

            favouriteAdapter.setOnItemClickListener { adapter, _, position ->
                ARouter.getInstance()
                    .build(RoutePath.Club.ClubDetailActivity)
                    .withObject(CLUB_DETAIL, adapter.data[position])
                    .navigation()
            }
        }
    }

    class BannerAdapter : BaseBannerAdapter<String>() {
        override fun bindData(
            holder: BaseViewHolder<String>?,
            data: String?,
            position: Int,
            pageSize: Int
        ) {
            holder?.findViewById<ImageView>(R.id.bannerImg)?.load(data)
        }

        override fun getLayoutId(viewType: Int): Int {
            return R.layout.item_banner
        }

    }
}