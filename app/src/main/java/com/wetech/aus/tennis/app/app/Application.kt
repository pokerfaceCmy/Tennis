package com.wetech.aus.tennis.app.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.AppUtils
import com.google.firebase.FirebaseApp
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.wetech.aus.tennis.app.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/14 11:19
 * @Desc: Application
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
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(1)
            .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
            .tag(AppUtils.getAppName()) // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    Logger.log(priority, tag, message, t)
                }
            })
        }
//        else {
//            Timber.plant(FileTree())
//        }
    }

}