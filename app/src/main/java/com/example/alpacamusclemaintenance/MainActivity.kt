package com.example.alpacamusclemaintenance

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.alpacamusclemaintenance.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(FirstFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_exercise -> {
                loadFragment(ExerciseFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_record -> {
                loadFragment(ThirdFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_feed -> {
                loadFragment(FeedFragment())
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

        setSupportActionBar(toolbar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        loadFragment(FirstFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
