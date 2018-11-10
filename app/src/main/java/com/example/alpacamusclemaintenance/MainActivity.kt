package com.example.alpacamusclemaintenance

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import com.example.alpacamusclemaintenance.ui.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_exercise -> {
                loadFragment(ExerciseFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_record -> {
                loadFragment(RecordFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_feed -> {
                loadFragment(FeedFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bug_report -> {
                loadFragment(WebViewFragment.newInstance("https://github.com/alpaca0984/AlpacaMuscleMaintenance"))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {
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

        loadFragment(HomeFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
