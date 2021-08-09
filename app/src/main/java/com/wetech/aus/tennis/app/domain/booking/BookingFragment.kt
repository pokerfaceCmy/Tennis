package com.wetech.aus.tennis.app.domain.booking

import androidx.recyclerview.widget.LinearLayoutManager
import com.poker.common.base.BaseFragment
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentBookingBinding
import com.wetech.aus.tennis.app.domain.booking.adapter.BookingAdapter
import com.wetech.aus.tennis.app.domain.booking.repository.bean.BookingRequest
import com.wetech.aus.tennis.app.domain.booking.vm.BookingViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:13
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class BookingFragment : BaseFragment<FragmentBookingBinding>() {
    private var pageNum = 1
    private val viewModel by getViewModel(BookingViewModel::class.java) {
        queryBookingListLD.observe(mLifecycleOwner, {
            if (pageNum == 1) {
                if (it?.hasNextPage == true) binding.refreshLayout.finishRefresh(true) else binding.refreshLayout.finishRefreshWithNoMoreData()
                bookingAdapter.setList(it?.list)
            } else {
                if (it?.hasNextPage == true) binding.refreshLayout.finishLoadMore(true) else binding.refreshLayout.finishLoadMoreWithNoMoreData()
                it?.list?.let { it1 -> bookingAdapter.addData(it1) }
            }
        })
    }

    private val bookingAdapter by lazy {
        BookingAdapter()
    }

    override fun init() {
        viewModel.queryBookingList(
            BookingRequest(
                pageNum = pageNum,
                pageSize = 10
            )
        )
        binding.apply {
            toolBar.tvTitle.text = getString(R.string.my_booking)
            recyclerView.layoutManager = LinearLayoutManager(mContext)
            recyclerView.adapter = bookingAdapter

            refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onRefresh(refreshLayout: RefreshLayout) {
                    pageNum = 1
                    viewModel.queryBookingList(
                        BookingRequest(
                            pageNum = pageNum,
                            pageSize = 10
                        )
                    )
                }

                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    pageNum += 1
                    viewModel.queryBookingList(
                        BookingRequest(
                            pageNum = pageNum,
                            pageSize = 10
                        )
                    )
                }
            })
        }
    }

}