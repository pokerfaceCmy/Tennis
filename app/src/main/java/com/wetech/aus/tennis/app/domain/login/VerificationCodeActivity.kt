package com.wetech.aus.tennis.app.domain.login

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SPUtils
import com.poker.common.base.BaseActivity
import com.poovam.pinedittextfield.PinField
import com.wetech.aus.tennis.app.databinding.ActivityVerificationCodeBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.login.repository.bean.LoginRequest
import com.wetech.aus.tennis.app.domain.login.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@Route(path = RoutePath.Login.VerificationCodeActivity)
@AndroidEntryPoint
class VerificationCodeActivity : BaseActivity<ActivityVerificationCodeBinding>() {
    @Autowired
    lateinit var phone: String

    @Autowired
    lateinit var phonePrefix: String

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
                    viewModel.login(
                        LoginRequest(
                            phonePrefix = phonePrefix,
                            phone = phone,
                            code = enteredText
                        )
                    )
                    return true
                }
            }
        }
    }

}