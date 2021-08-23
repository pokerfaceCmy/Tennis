package com.wetech.aus.tennis.app.domain.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.alibaba.android.arouter.facade.annotation.Route
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivityRetrievePswBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Login.RetrievePswActivity)
@AndroidEntryPoint
class RetrievePswActivity : BaseActivity<ActivityRetrievePswBinding>() {
    private var phonePrefix = "86"

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
            btnBack.setOnClickListener { finish() }

            btnNext.setOnClickListener {

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