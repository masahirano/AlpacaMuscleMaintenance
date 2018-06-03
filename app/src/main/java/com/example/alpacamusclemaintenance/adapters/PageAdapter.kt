package com.example.alpacamusclemaintenance.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.alpacamusclemaintenance.fragments.FirstFragment
import com.example.alpacamusclemaintenance.fragments.SecondFragment
import com.example.alpacamusclemaintenance.fragments.ThirdFragment
import com.example.alpacamusclemaintenance.fragments.WebViewFragment

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment()
            else -> WebViewFragment()
        }
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "First Tab"
            1 -> "Second Tab"
            2 -> "Third Tab"
            else -> "Repo Tab"
        }
    }

}
