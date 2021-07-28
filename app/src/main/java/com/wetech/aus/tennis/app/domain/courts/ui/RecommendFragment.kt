package com.wetech.aus.tennis.app.domain.courts.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.poker.common.base.BaseFragment
import com.wetech.aus.tennis.app.databinding.FragmentRecommendBinding
import com.wetech.aus.tennis.app.domain.courts.adapter.RecommendAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/19 9:25
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class RecommendFragment : BaseFragment<FragmentRecommendBinding>() {
    private val recommendAdapter by lazy {
        RecommendAdapter()
    }

    override fun init() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(mContext)
            recyclerView.adapter = recommendAdapter

            recommendAdapter.setList(mutableListOf("", "", "", "", ""))
        }
    }
}