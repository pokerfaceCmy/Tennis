package com.wetech.aus.tennis.app.domain

import android.content.Intent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.jeremyliao.liveeventbus.LiveEventBus
import com.king.zxing.CameraScan
import com.king.zxing.CaptureActivity
import com.poker.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityMainBinding
import com.wetech.aus.tennis.app.domain.booking.BookingFragment
import com.wetech.aus.tennis.app.domain.courts.CourtsFragment
import com.wetech.aus.tennis.app.domain.courts.ui.MapsFragment.Companion.MAP_FRAGMENT_RESUME
import com.wetech.aus.tennis.app.domain.home.HomeFragment
import com.wetech.aus.tennis.app.domain.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RoutePath.MainActivity)
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val requestCodeScan = 0X01
            val data = it.data
            when (it.resultCode) {
                requestCodeScan -> {
                    CameraScan.parseScanResult(data)
                }
            }
        }

    override fun init() {
        binding.apply {
            viewPage.adapter = ViewPager2Adapter(supportFragmentManager, lifecycle)
            viewPage.offscreenPageLimit = 4
            (viewPage.getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER

            //点击底部导航栏时 控制ViewPager的变化
                bottomNav.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> viewPage.setCurrentItem(0, true)
                    R.id.menu_courts -> viewPage.setCurrentItem(1, true)
                    R.id.menu_empty -> {
                        activityResultLauncher.launch(Intent(mContext, CaptureActivity::class.java))
                    }
                    R.id.menu_booking -> viewPage.setCurrentItem(2, true)
                    R.id.menu_Profile -> viewPage.setCurrentItem(3, true)
                }
                true
            }
            // 当ViewPager切换页面时，改变底部导航栏的状态
            viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    var mPosition = position
                    if (position >= 2) {
                        mPosition++
                    }
                    bottomNav.menu.getItem(mPosition).isChecked = true
                }
            })
            //禁止滑动
            viewPage.isUserInputEnabled = false

            //如果是地图页面的话,隐藏底部导航栏
            LiveEventBus.get(MAP_FRAGMENT_RESUME, Int::class.java)
                .observe(mLifecycleOwner, {
                    when (it) {
                        0 -> {
                            bottomNav.visibility = View.GONE
                            imgScan.visibility = View.GONE
                        }
                        1 -> {
                            bottomNav.visibility = View.VISIBLE
                            imgScan.visibility = View.VISIBLE
                        }
                    }

                })
        }

    }

    class ViewPager2Adapter(supportFragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(supportFragmentManager, lifecycle) {
        override fun getItemCount(): Int {
            return 4
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> CourtsFragment()
                2 -> BookingFragment()
                3 -> ProfileFragment()
                else -> {
                    Fragment()
                }
            }
        }

    }
}

