package com.wetech.aus.tennis.app.domain.login

import com.alibaba.android.arouter.launcher.ARouter
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivitySmsLoginBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.login.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmsLoginActivity : BaseActivity<ActivitySmsLoginBinding>() {


    override fun init() {
        binding.apply {
            btnGetCode.setOnClickListener {
                ARouter.getInstance()
                    .build(RoutePath.Login.VerificationCodeActivity)
                    .navigation()
            }
            tvPasswordLogin.setOnClickListener {
                ARouter.getInstance()
                    .build(RoutePath.Login.PasswordLoginActivity)
                    .navigation()
            }
            clSelectCountryCode.setOnClickListener {
                ARouter.getInstance()
                    .build(RoutePath.Login.PrefixActivity)
                    .navigation()
            }
        }
    }
}