package com.example.alpacamusclemaintenance.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.alpacamusclemaintenance.ui.PushUpFragment
import com.example.alpacamusclemaintenance.ui.SquatFragment

class ExercisePageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PushUpFragment()
            else -> SquatFragment()
        }
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "pushUp"
            else -> "squat"
        }
    }
}
