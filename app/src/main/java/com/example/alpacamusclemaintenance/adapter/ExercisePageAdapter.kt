package com.example.alpacamusclemaintenance.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.alpacamusclemaintenance.ui.PushUpFragment
import com.example.alpacamusclemaintenance.ui.SquatFragment

class ExercisePageAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
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
