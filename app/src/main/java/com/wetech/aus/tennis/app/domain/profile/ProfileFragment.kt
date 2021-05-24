package com.wetech.aus.tennis.app.domain.profile

import coil.load
import com.pcyun.common.base.BaseFragment
import com.wetech.aus.tennis.app.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:11
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun init() {
        binding.apply {
            imgAvatar.load("https://avatars.githubusercontent.com/u/43669001?v=4")
            tvUserName.text = "pokerfaceCmy"
            ratingBar.rating = 3f
            tvGrowthValueContent.text = "再积174经验可升级>VIP2会员"
        }
    }
}