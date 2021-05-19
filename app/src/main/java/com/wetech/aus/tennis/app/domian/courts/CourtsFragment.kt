package com.wetech.aus.tennis.app.domian.courts

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.pcyun.common.base.BaseFragment
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.FragmentCourtsBinding
import com.wetech.aus.tennis.app.domian.courts.ui.MapsFragment
import com.wetech.aus.tennis.app.domian.courts.ui.RecommendFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/18 14:12
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
@AndroidEntryPoint
class CourtsFragment : BaseFragment<FragmentCourtsBinding>() {

    override fun init() {
        binding.apply {
            val titles = arrayOf(getString(R.string.recommend), getString(R.string.locations))
            toolBar.tvTitle.text = getString(R.string.courts)
            viewPager2.isUserInputEnabled = false
            viewPager2.adapter = object : FragmentStateAdapter(this@CourtsFragment) {
                override fun getItemCount(): Int {
                    return 2
                }

                override fun createFragment(position: Int): Fragment {
                    return when (position) {
                        0 -> RecommendFragment()
                        1 -> MapsFragment()
                        else -> Fragment()
                    }
                }
            }
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = titles[position]
            }.attach()
        }
    }
}