package com.wetech.aus.tennis.app.domain.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lwjfork.code.CodeEditText
import com.pcyun.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivityVerificationCodeBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Companion.Login.VerificationCodeActivity)
@AndroidEntryPoint
class VerificationCodeActivity : BaseActivity<ActivityVerificationCodeBinding>() {
    override fun init() {
        binding.apply {
            btnBack.setOnClickListener { finish() }
            codeEditText.setOnTextChangedListener(object : CodeEditText.OnTextChangedListener {
                override fun onCodeChanged(changeText: CharSequence?) {

                }

                override fun onInputCompleted(text: CharSequence?) {
                    // FIXME: 2021/7/28 just for test
                    if (text.toString() == "941021") {
                        ARouter.getInstance()
                            .build(RoutePath.MainActivity)
                            .navigation()
                    }
                    // TODO: 2021/7/28 校验验证码
                }
            })
        }
    }
}