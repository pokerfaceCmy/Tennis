package com.wetech.aus.tennis.app.domain.profile

import coil.load
import com.alibaba.android.arouter.facade.annotation.Route
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityEditProfileBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.login.repository.bean.UserInfoDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Route(path = RoutePath.Profile.EditProfileActivity)
@AndroidEntryPoint
class EditProfileActivity : BaseActivity<ActivityEditProfileBinding>() {
    @Inject
    lateinit var userInfoDao: UserInfoDao

    override fun init() {
        binding.apply {
            toolBar.btnBack.setOnClickListener { finish() }
            toolBar.tvTitle.text = getString(R.string.edit_profile)

            lifecycleSupportedScope.launch(Dispatchers.Main) {
                val userInfo = withContext(Dispatchers.IO) { userInfoDao.query() }
                userInfo?.apply {
                    imgAvatar.load(avatar)
                }
            }
        }
    }
}