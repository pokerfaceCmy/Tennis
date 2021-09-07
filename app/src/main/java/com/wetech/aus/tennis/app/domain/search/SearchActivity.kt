package com.wetech.aus.tennis.app.domain.search

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivitySearchBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.club.ClubDetailActivity
import com.wetech.aus.tennis.app.domain.courts.adapter.RecommendAdapter
import com.wetech.aus.tennis.app.domain.home.repository.bean.ClubListRequest
import com.wetech.aus.tennis.app.domain.home.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.SearchActivity)
@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    private val viewModel by getViewModel(HomeViewModel::class.java) {
        searchClubLD.observe(mLifecycleOwner, {
            searchAdapter.setList(it?.list)
        })
    }

    private val searchAdapter by lazy {
        RecommendAdapter()
    }

    override fun init() {
        binding.apply {
            btnSearch.setOnClickListener {
                if (appCompatEditText.text.isNullOrBlank()) return@setOnClickListener

                viewModel.searchClub(appCompatEditText.text.toString())
            }

            rvSearch.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
            rvSearch.adapter = searchAdapter

            searchAdapter.addChildClickViewIds(R.id.ivLike)
            searchAdapter.setOnItemClickListener { adapter, _, position ->
                ARouter.getInstance()
                    .build(RoutePath.Club.ClubDetailActivity)
                    .withObject(ClubDetailActivity.CLUB_DETAIL, adapter.data[position])
                    .navigation()
            }
            searchAdapter.setOnItemChildClickListener { _, view, position ->
                when (view.id) {
                    R.id.ivLike -> {
                        val data = searchAdapter.data[position]
                        val newData = data.copy(
                            enjoy = if (data.enjoy == 1) 2 else 1
                        )
                        val enjoy = data.enjoy
                        viewModel.likeClub(data.id, if (enjoy == 1) "2" else "1")

                        viewModel.likeClubLD.observe(mLifecycleOwner, {
                            searchAdapter.setData(position, newData)
                            viewModel.queryFavouritesClubList(
                                ClubListRequest(
                                pageNum = 1,
                                enjoy = 1
                            )
                            )
                        })
                    }
                }
            }

        }
    }
}