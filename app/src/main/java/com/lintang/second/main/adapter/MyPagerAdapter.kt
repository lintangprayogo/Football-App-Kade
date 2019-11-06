package com.lintang.second.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lintang.second.base.ui.BaseFragment

@Suppress("DEPRECATION")
class MyPagerAdapter(
    private val fm: FragmentManager,
    private val fragments: List<BaseFragment>,
    private val names: List<String>
) :
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return names[position]
    }
}