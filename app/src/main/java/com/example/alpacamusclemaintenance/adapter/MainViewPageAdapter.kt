package com.example.alpacamusclemaintenance.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.alpacamusclemaintenance.ui.*

class MainViewPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

  private val bugReportFragment = WebViewFragment.newInstance("https://github.com/alpaca0984/AlpacaMuscleMaintenance")

  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> HomeFragment()
      1 -> ExerciseFragment()
      2 -> RecordFragment()
      3 -> FeedFragment()
      else -> bugReportFragment
    }
  }

  // Show 5 total pages.
  override fun getCount(): Int = 5

  override fun getPageTitle(position: Int): CharSequence {
    return when (position) {
      0 -> "Home"
      1 -> "Exercise"
      2 -> "Record"
      3 -> "Feed"
      else -> "BugReport"
    }
  }
}
