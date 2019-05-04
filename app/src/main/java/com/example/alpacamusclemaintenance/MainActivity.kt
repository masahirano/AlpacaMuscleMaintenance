package com.example.alpacamusclemaintenance

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.databinding.DataBindingUtil
import com.example.alpacamusclemaintenance.adapter.MainViewPageAdapter
import com.example.alpacamusclemaintenance.databinding.ActivityMainBinding
import com.example.alpacamusclemaintenance.ui.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    // From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/MainActivity.kt
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private lateinit var binding: ActivityMainBinding

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
//                loadFragment(HomeFragment())
                binding.pager.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_exercise -> {
//                loadFragment(ExerciseFragment())
                binding.pager.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_record -> {
//                loadFragment(RecordFragment())
                binding.pager.setCurrentItem(2, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_feed -> {
//                loadFragment(FeedFragment())
                binding.pager.setCurrentItem(3, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bug_report -> {
//                loadFragment(WebViewFragment.newInstance("https://github.com/alpaca0984/AlpacaMuscleMaintenance"))
                binding.pager.setCurrentItem(4, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

//    private fun loadFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.content, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.pager.setPagingEnabled(false)
        binding.pager.adapter = MainViewPageAdapter(supportFragmentManager)

        setSupportActionBar(toolbar)
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
