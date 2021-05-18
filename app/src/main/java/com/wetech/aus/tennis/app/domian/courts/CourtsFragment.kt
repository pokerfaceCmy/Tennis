package com.wetech.aus.tennis.app.domian.courts

import com.pcyun.common.base.BaseFragment
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentCourtsBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:12
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class CourtsFragment : BaseFragment<FragmentCourtsBinding>() {

    override fun init() {
        binding.apply {
            toolBar.tvTitle.text = getString(R.string.courts)
        }
    }
}