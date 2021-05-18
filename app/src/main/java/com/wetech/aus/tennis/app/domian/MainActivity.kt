package com.wetech.aus.tennis.app.domian

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.pcyun.common.base.BaseActivity
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityMainBinding
import com.wetech.aus.tennis.app.domian.booking.BookingFragment
import com.wetech.aus.tennis.app.domian.courts.CourtsFragment
import com.wetech.aus.tennis.app.domian.home.HomeFragment
import com.wetech.aus.tennis.app.domian.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
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
                        scanQRCode()
                    }
                    R.id.menu_booking -> viewPage.setCurrentItem(3, true)
                    R.id.menu_Profile -> viewPage.setCurrentItem(4, true)
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
        }
    }

    private fun scanQRCode() {
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

