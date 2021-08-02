package com.wetech.aus.tennis.app.domain.home

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import coil.load
import com.poker.common.base.BaseFragment
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentHomeBinding
import com.wetech.aus.tennis.app.domain.home.adapter.FavouriteAdapter
import com.wetech.aus.tennis.app.domain.home.adapter.RecommendAdapter
import com.wetech.aus.tennis.app.domain.home.vm.HomeViewModel
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:09
 * @Desc: TODO
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
    }

    override fun init() {
        Timber.d("init")
        viewModel.getBanner()

        binding.apply {
            toolBar.tvTitle.text = getString(R.string.hi_tennis)
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
            recommendAdapter.setList(mutableListOf("", "", "", ""))


            rvFavourites.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            rvFavourites.adapter = favouriteAdapter
            val pagerSnapHelper1 = PagerSnapHelper()
            pagerSnapHelper1.attachToRecyclerView(rvFavourites)
            favouriteAdapter.setList(mutableListOf("", "", "", ""))
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