package com.wetech.aus.tennis.app.domain.courts.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.poker.common.base.BaseFragment
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.wetech.aus.tennis.app.databinding.FragmentRecommendBinding
import com.wetech.aus.tennis.app.domain.courts.adapter.RecommendAdapter
import com.wetech.aus.tennis.app.domain.courts.vm.CourtsViewModel
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListRequest
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/19 9:25
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class RecommendFragment : BaseFragment<FragmentRecommendBinding>() {
    private var pageNum = 1
    private val viewModel by getViewModel(CourtsViewModel::class.java) {
        queryRecommendClubListLD.observe(mLifecycleOwner, {
            if (pageNum == 1) {
                binding.refreshLayout.finishRefresh()
                recommendAdapter.setList(it?.list)
            } else {
                binding.refreshLayout.finishLoadMore()
                it?.list?.let { it1 -> recommendAdapter.addData(it1) }
            }

        })
    }
    private val recommendAdapter by lazy {
        RecommendAdapter()
    }

    override fun init() {
        binding.apply {
            viewModel.queryRecommendClubList(
                ClubListRequest(
                    pageNum = pageNum,
                    pageSize = 10
                )
            )
            recyclerView.layoutManager = LinearLayoutManager(mContext)
            recyclerView.adapter = recommendAdapter

            refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onRefresh(refreshLayout: RefreshLayout) {
                    pageNum = 1
                    viewModel.queryRecommendClubList(
                        ClubListRequest(
                            pageNum = pageNum,
                            pageSize = 10
                        )
                    )
                }

                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    pageNum += 1
                    viewModel.queryRecommendClubList(
                        ClubListRequest(
                            pageNum = pageNum,
                            pageSize = 10
                        )
                    )
                }
            })
        }
    }
}