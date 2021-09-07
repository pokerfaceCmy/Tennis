@file:Suppress("unused")

package com.poker.plugin

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/6/29 10:02
 * @Desc: 统一管理项目依赖项
 * @GitHub：https://github.com/pokerfaceCmy
 */
object Deps {

    const val androidGradlePlugin = "com.android.tools.build:gradle:4.2.2"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.0.9"

    const val material = "com.google.android.material:material:1.3.0"

    object Kotlin {
        private const val version = "1.5.21"

        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"

        object Coroutines {
            // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-android
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1"
        }
    }

    object Jetpack {
        object Hilt {
            private const val version = "2.38.1"

            const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
            const val hiltAndroid = "com.google.dagger:hilt-android:$version"
            const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
            const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
        }

        object Viewmodel {
            private const val version = "2.3.1"

            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val hilt = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
        }

        object Livedata {
            private const val version = "2.3.1"

            const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val core = "androidx.lifecycle:lifecycle-livedata-core-ktx:$version"
        }

        object Room {
            private const val version = "2.3.0"

            const val ktx = "androidx.room:room-ktx:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val roomCompiler = "androidx.room:room-compiler:$version"
        }

        object DataStore {
            private const val version = "1.0.0-alpha01"

            const val preferences = "androidx.datastore:datastore-preferences:$version"
        }
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.6.0"
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    }

    object Okhttp {
        private const val version = "4.9.1"

        /**
         * @see <a href="https://github.com/square/okhttp">
         *      OkHttp is an HTTP client that’s efficient</a>
         */
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"

        /**
         * @see <a href="https://github.com/square/retrofit">
         *      A type-safe HTTP client for Android and Java.</a>
         */
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converter_moshi = "com.squareup.retrofit2:converter-moshi:$version"
//        const val converter_gson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Router {
        private const val version = "1.5.2"

        /**
         * @see <a href="https://github.com/alibaba/ARouter">
         *      帮助 Android App 进行组件化改造的框架 —— 支持模块间的路由、通信、解耦</a>
         */
        const val arouterApi = "com.alibaba:arouter-api:$version"
        const val arouterCompiler = "com.alibaba:arouter-compiler:$version"
    }

    object Json {
        private const val version = "1.12.0"

        /**
         * @see <a href="https://github.com/square/moshi">
         *     lottie 是一个Json解析库</a>
         */
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Lottie {
        private const val version = "3.7.0"

        /**
         * @see <a href="https://github.com/airbnb/lottie-android">
         *     lottie 是一个 Android 动画库，通过 Json文件来实现复杂的动画效果</a>
         */
        const val lottie = "com.airbnb.android:lottie:$version"
    }

    object Image {
        private const val version = "1.3.1"

        /**
         * @see <a href="https://github.com/coil-kt/coil/blob/main/README-zh.md">
         *     Coil 是一个 Android 图片加载库，通过 Kotlin 协程的方式加载图片</a>
         */
        const val coil = "io.coil-kt:coil:$version"
    }

    object Log {
        const val timber = "com.jakewharton.timber:timber:4.7.1"
        const val logger = "com.orhanobut:logger:2.2.0"
    }

    object Permissions {
        private const val version = "4.8.0"

        // https://github.com/permissions-dispatcher/PermissionsDispatcher
        const val permissionsdispatcher =
            "com.github.permissions-dispatcher:permissionsdispatcher:$version"
        const val processor =
            "com.github.permissions-dispatcher:permissionsdispatcher-processor:$version"
        const val ktx = "com.github.permissions-dispatcher:ktx:1.0.5"
    }

    object Refresh {
        private const val version = "2.0.3"

        const val kernel = "com.scwang.smart:refresh-layout-kernel:$version"
        const val footer = "com.scwang.smart:refresh-footer-classics:$version"
        const val header = "com.scwang.smart:refresh-header-classics:$version"
    }

    object Util {
        /**
         * @see <a href="https://github.com/JessYanCoding/AndroidAutoSize">
         *     今日头条屏幕适配方案终极版，一个极低成本的 Android 屏幕适配方案</a>
         */
        const val autosize = "com.github.JessYanCoding:AndroidAutoSize:v1.2.1"

        /**
         * @see <a href="https://github.com/jenly1314/ZXingLite">
         *     基于ZXing库优化扫码和生成二维码/条形码功能</a>
         */
        const val zxingLite = "com.github.jenly1314:zxing-lite:2.1.0"

        const val eventBus = "io.github.jeremyliao:live-event-bus-x:1.8.0"

        /**
         * @see <a href="https://github.com/Ye-Miao/StatusBarUtil">
         *     Android沉浸式状态栏，支持状态栏渐变色，纯色， 全屏，亮光、暗色模式，适配android 4.4 -10.0机型，支持刘海屏，滴水屏</a>
         */
        const val statusBarUtil = "com.github.Ye-Miao:StatusBarUtil:1.7.5"

        /**
         * @see <a href="https://github.com/li-xiaojun/XPopup">
         *     替代Dialog，PopupWindow，PopupMenu，BottomSheet，DrawerLayout，Spinner等组件</a>
         */
        const val xPopup = "com.github.li-xiaojun:XPopup:2.4.4"

        /**
         * @see <a href="https://github.com/Blankj/AndroidUtilCode/blob/master/lib/utilcode/README-CN.md">
         *     Android开发工具类</a>
         */
        const val utilcode = "com.blankj:utilcodex:1.30.6"

        /**
         * @see <a href="https://github.com/CymChad/BaseRecyclerViewAdapterHelper/blob/master/readme/0-BaseRecyclerViewAdapterHelper.md">
         *     一个强大并且灵活的RecyclerViewAdapter</a>
         */
        const val baseRecyclerViewAdapterHelper =
            "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4"

        /**
         * @see <a href="https://github.com/AAChartModel/AAChartCore-Kotlin/blob/master/CHINESE-README.md">
         *     Android 图表控件</a>
         */
        const val AAChartCore = "com.github.AAChartModel:AAChartCore-Kotlin:-SNAPSHOT"

    }

    object Braintree {
        private const val version = ""
        const val drop_in = "com.braintreepayments.api:drop-in:5.2.2"
    }
}
