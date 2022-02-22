package com.kakaobank.imageselector.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BaseFragmentPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList: ArrayList<Fragment> = ArrayList()

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    override fun getItemCount(): Int = fragmentList.size

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }
}