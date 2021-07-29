package com.wetech.aus.tennis.app.domain.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivityPasswordLoginBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Login.PasswordLoginActivity)
@AndroidEntryPoint
class PasswordLoginActivity : BaseActivity<ActivityPasswordLoginBinding>() {
    override fun init() {
        binding.apply {
            btnLogin.setOnClickListener {
                // TODO: 2021/7/28 登录
            }

            tvSmsLogin.setOnClickListener {

            }

            tvForgotPsw.setOnClickListener {
                ARouter.getInstance()
                    .build(RoutePath.Login.RetrievePswActivity)
                    .navigation()
            }

        }
    }
}