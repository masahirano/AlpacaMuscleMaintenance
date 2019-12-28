package com.example.alpacamusclemaintenance

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.alpacamusclemaintenance.adapter.MainViewPageAdapter
import com.example.alpacamusclemaintenance.databinding.ActivityMainBinding
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector() = androidInjector

  private lateinit var binding: ActivityMainBinding

  private val mOnNavigationItemSelectedListener =
    BottomNavigationView.OnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.navigation_home -> {
          binding.pager.setCurrentItem(0, false)
          return@OnNavigationItemSelectedListener true
        }
        R.id.navigation_exercise -> {
          binding.pager.setCurrentItem(1, false)
          return@OnNavigationItemSelectedListener true
        }
        R.id.navigation_record -> {
          binding.pager.setCurrentItem(2, false)
          return@OnNavigationItemSelectedListener true
        }
        R.id.navigation_feed -> {
          binding.pager.setCurrentItem(3, false)
          return@OnNavigationItemSelectedListener true
        }
        R.id.navigation_bug_report -> {
          binding.pager.setCurrentItem(4, false)
          return@OnNavigationItemSelectedListener true
        }
      }
      false
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    binding.pager.setPagingEnabled(false)
    binding.pager.offscreenPageLimit = 4
    binding.pager.adapter = MainViewPageAdapter(supportFragmentManager)

    setSupportActionBar(toolbar)
    binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.toolbar, menu)
    return super.onCreateOptionsMenu(menu)
  }
}
