package com.wetech.aus.tennis.app.domain.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.contract.ActivityResultContracts
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivitySmsLoginBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Login.SmsLoginActivity)
@AndroidEntryPoint
class SmsLoginActivity : BaseActivity<ActivitySmsLoginBinding>() {
    private var phonePrefix = "86"
    private var phoneNum = ""

    @SuppressLint("SetTextI18n")
    val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                phonePrefix = data?.getStringExtra("phonePrefix") ?: "86"

                binding.tvCountryCode.text = "+$phonePrefix"
            }
        }

    override fun init() {
        binding.apply {
            editPhoneNum.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    phoneNum = s.toString()
                    if (s?.length != 0) {
                        btnGetCode.setBackgroundColor(Color.parseColor("#3291F8"))
                    } else {
                        btnGetCode.setBackgroundColor(Color.parseColor("#DFDFDF"))
                    }
                }

            })
            btnGetCode.setOnClickListener {
                "18617131916"
                ARouter.getInstance()
                    .build(RoutePath.Login.VerificationCodeActivity)
                    .withString("phone", phoneNum)
                    .withString("phonePrefix", phonePrefix)
                    .navigation()
            }
            tvPasswordLogin.setOnClickListener {
                ARouter.getInstance()
                    .build(RoutePath.Login.PasswordLoginActivity)
                    .navigation()
            }

            clSelectCountryCode.setOnClickListener {
                openPrefixActivityForResult()
            }
        }
    }

    private fun openPrefixActivityForResult() {
        val intent = Intent(this, PrefixActivity::class.java)
        launcher.launch(intent)
    }

}