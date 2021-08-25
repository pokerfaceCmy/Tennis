package com.wetech.aus.tennis.app.domain.profile

import coil.load
import com.poker.common.base.BaseFragment
import com.wetech.aus.tennis.app.databinding.FragmentProfileBinding
import com.wetech.aus.tennis.app.domain.login.repository.bean.UserInfoDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:11
 * @Desc:
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    @Inject
    lateinit var userInfoDao: UserInfoDao

    override fun init() {

        binding.apply {
            lifecycleSupportedScope.launch(Dispatchers.Main) {
                val userInfo = withContext(Dispatchers.IO) { userInfoDao.query() }
                userInfo?.apply {
                    imgAvatar.load(avatar)
                    tvUserName.text = userName
                    ratingBar.rating = starts?.toFloat() ?: 0f
                    progressBar.progress = 50
                    tvGrowthValueContent.text = "再积174经验可升级>VIP2会员"
                }
            }
            imgAvatar.setOnClickListener {
//                ARouter.getInstance()
//                    .build("")
//                    .navigation()
            }
        }
    }
}