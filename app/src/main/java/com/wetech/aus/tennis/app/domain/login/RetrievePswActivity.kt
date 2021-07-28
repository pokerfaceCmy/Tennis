package com.wetech.aus.tennis.app.domain.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivityRetrievePswBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Companion.Login.RetrievePswActivity)
@AndroidEntryPoint
class RetrievePswActivity : BaseActivity<ActivityRetrievePswBinding>() {

    override fun init() {
        binding.apply {
            btnNext.setOnClickListener {

            }
        }
    }
}