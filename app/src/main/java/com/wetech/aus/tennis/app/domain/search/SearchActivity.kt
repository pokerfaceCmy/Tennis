package com.wetech.aus.tennis.app.domain.search

import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    override fun init() {
        binding.apply {
            appCompatEditText
        }
    }
}