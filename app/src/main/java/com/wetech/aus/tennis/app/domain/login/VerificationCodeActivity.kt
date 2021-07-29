package com.wetech.aus.tennis.app.domain.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.poker.common.base.BaseActivity
import com.poovam.pinedittextfield.PinField
import com.wetech.aus.tennis.app.databinding.ActivityVerificationCodeBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.login.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@Route(path = RoutePath.Login.VerificationCodeActivity)
@AndroidEntryPoint
class VerificationCodeActivity : BaseActivity<ActivityVerificationCodeBinding>() {
    private val viewModel by getViewModel(LoginViewModel::class.java) {
        sendSmsFailedLD.observe(mLifecycleOwner, {
            Timber.e(it)
        })
    }

    override fun init() {
        viewModel.sendSms()
        binding.apply {
            btnBack.setOnClickListener { finish() }
            codeEditText.onTextCompleteListener = object : PinField.OnTextCompleteListener {
                override fun onTextComplete(enteredText: String): Boolean {
                    if (enteredText == "941021") {
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