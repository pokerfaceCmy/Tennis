package com.wetech.aus.tennis.app.domain.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.poker.common.base.BaseActivity
import com.poovam.pinedittextfield.PinField
import com.wetech.aus.tennis.app.databinding.ActivityVerificationCodeBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Companion.Login.VerificationCodeActivity)
@AndroidEntryPoint
class VerificationCodeActivity : BaseActivity<ActivityVerificationCodeBinding>() {
    override fun init() {
        binding.apply {
            btnBack.setOnClickListener { finish() }
            codeEditText.onTextCompleteListener = object : PinField.OnTextCompleteListener {
                override fun onTextComplete(enteredText: String): Boolean {
                    if (enteredText == "1021") {
                        ARouter.getInstance()
                            .build(RoutePath.MainActivity)
                            .navigation()
                    }
                    return true
                }
            }
        }
    }
}