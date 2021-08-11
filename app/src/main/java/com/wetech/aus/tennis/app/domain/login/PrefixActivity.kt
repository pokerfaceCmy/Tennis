package com.wetech.aus.tennis.app.domain.login

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.alibaba.android.arouter.facade.annotation.Route
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityPrefixBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.login.adapter.PrefixAdapter
import com.wetech.aus.tennis.app.domain.login.repository.bean.PrefixResponse
import com.wetech.aus.tennis.app.domain.login.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.Login.PrefixActivity)
@AndroidEntryPoint
class PrefixActivity : BaseActivity<ActivityPrefixBinding>() {

    private val viewModel by getViewModel(LoginViewModel::class.java) {
        getPrefixLD.observe(mLifecycleOwner, {
            prefixAdapter.setList(it?.list)
        })
    }

    private val prefixAdapter by lazy {
        PrefixAdapter()
    }

    override fun init() {
        viewModel.getCountryMobilePrefix()
        binding.apply {
            toolBar.btnBack.setOnClickListener { finish() }
            toolBar.tvTitle.text = getString(R.string.choose_a_country)

            recyclerView.layoutManager = LinearLayoutManager(mContext, VERTICAL, false)
            recyclerView.adapter = prefixAdapter

            prefixAdapter.setOnItemClickListener { adapter, _, position ->
                val data = adapter.data[position] as PrefixResponse.Data
                setResult(Activity.RESULT_OK, Intent().putExtra("phonePrefix", data.code))
                finish()
            }
        }
    }
}