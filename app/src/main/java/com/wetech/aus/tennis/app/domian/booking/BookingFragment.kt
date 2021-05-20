package com.wetech.aus.tennis.app.domian.booking

import androidx.recyclerview.widget.LinearLayoutManager
import com.pcyun.common.base.BaseFragment
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentBookingBinding
import com.wetech.aus.tennis.app.domian.booking.adapter.BookingAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:13
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class BookingFragment : BaseFragment<FragmentBookingBinding>() {
    private val bookingAdapter by lazy {
        BookingAdapter()
    }

    override fun init() {
        binding.apply {
            toolBar.tvTitle.text = getString(R.string.my_booking)
            recyclerView.layoutManager = LinearLayoutManager(mContext)
            recyclerView.adapter = bookingAdapter

            refreshLayout.autoRefresh()

            refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onRefresh(refreshLayout: RefreshLayout) {
                    bookingAdapter.setList(mutableListOf("", "", "", "", "", "", "", "", "", ""))
                    refreshLayout.finishRefresh()
                }

                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    bookingAdapter.addData(mutableListOf("", "", "", "", "", "", "", "", "", ""))
                    refreshLayout.finishLoadMore()
                }
            })
        }
    }

}