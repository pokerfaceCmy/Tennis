package com.wetech.aus.tennis.app.domian.booking

import com.pcyun.common.base.BaseFragment
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentBookingBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:13
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class BookingFragment : BaseFragment<FragmentBookingBinding>() {
    override fun init() {
        binding.apply {
            toolBar.tvTitle.text = getString(R.string.my_booking)
        }
    }

}