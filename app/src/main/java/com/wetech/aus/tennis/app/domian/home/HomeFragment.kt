package com.wetech.aus.tennis.app.domian.home

import com.pcyun.common.base.BaseFragment
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentHomeBinding
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
    override fun init() {
        binding.apply {
            toolBar.tvTitle.text = getString(R.string.hi_tennis)

        }
    }

}