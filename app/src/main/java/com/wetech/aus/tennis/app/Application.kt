package com.wetech.aus.tennis.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.AppUtils
import com.google.firebase.FirebaseApp
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/14 11:19
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initLog()
        initARouter()
        FirebaseApp.initializeApp(this)
    }

    private fun initARouter() {
        if (AppUtils.isAppDebug()) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    private fun initLog() {
        Logger.addLogAdapter(AndroidLogAdapter())
        if (AppUtils.isAppDebug()) Timber.plant(Timber.DebugTree())
    }

}