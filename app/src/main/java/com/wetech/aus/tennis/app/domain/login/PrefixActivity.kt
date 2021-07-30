package com.wetech.aus.tennis.app.domain.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivityPrefixBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.login.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Login.PrefixActivity)
@AndroidEntryPoint
class PrefixActivity : BaseActivity<ActivityPrefixBinding>() {
    private val viewModel by getViewModel(LoginViewModel::class.java) {
        getPrefixLD.observe(mLifecycleOwner, {

        })
    }

    override fun init() {
        viewModel.apply {
            getCountryMobilePrefix()
        }
    }
}