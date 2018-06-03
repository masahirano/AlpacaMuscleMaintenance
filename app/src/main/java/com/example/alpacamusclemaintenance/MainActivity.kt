package com.example.alpacamusclemaintenance

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.alpacamusclemaintenance.fragments.FirstFragment
import com.example.alpacamusclemaintenance.fragments.SecondFragment
import com.example.alpacamusclemaintenance.fragments.ThirdFragment
import com.example.alpacamusclemaintenance.fragments.WebViewFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(FirstFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_exercise -> {
                loadFragment(SecondFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_record -> {
                loadFragment(ThirdFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bug_report -> {
                loadFragment(WebViewFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)

//        tab.setupWithViewPager(pager)
//
//        tab.getTabAt(0)!!.setIcon(R.drawable.ic_home_white_24dp)
//        tab.getTabAt(1)!!.setIcon(R.drawable.ic_directions_run_white_24dp)
//        tab.getTabAt(2)!!.setIcon(R.drawable.ic_graphic_eq_white_24dp)
//        tab.getTabAt(3)!!.setIcon(R.drawable.ic_bug_report_white_24dp)
//        val fragmentAdapter = PageAdapter(supportFragmentManager)
//        pager.adapter = fragmentAdapter
//
//        tab.setupWithViewPager(pager)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadFragment(FirstFragment())
    }
}
