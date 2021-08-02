package com.wetech.aus.tennis.app.domain.splash

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.preferencesKey
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.poker.common.base.BaseActivity
import com.poker.common.interceptor.TokenInterceptor
import com.wetech.aus.tennis.app.databinding.ActivitySplashBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    @Inject
    lateinit var dataStore: DataStore<Preferences>

    override fun init() {
        val data = runBlocking { dataStore.data.first() }
        val token = data[preferencesKey<String>(name = TokenInterceptor.TOKEN)] ?: ""
        if (token == "") {
            ARouter.getInstance()
                .build(RoutePath.Login.SmsLoginActivity)
                .navigation(mContext, object : NavigationCallback {
                    override fun onFound(postcard: Postcard?) {
                    }

                    override fun onLost(postcard: Postcard?) {
                    }

                    override fun onArrival(postcard: Postcard?) {
                        finish()
                    }

                    override fun onInterrupt(postcard: Postcard?) {
                    }

                })
        } else {
            ARouter.getInstance()
                .build(RoutePath.MainActivity)
                .navigation(mContext, object : NavigationCallback {
                    override fun onFound(postcard: Postcard?) {
                    }

                    override fun onLost(postcard: Postcard?) {
                    }

                    override fun onArrival(postcard: Postcard?) {
                        finish()
                    }

                    override fun onInterrupt(postcard: Postcard?) {
                    }

                })
        }
    }
}