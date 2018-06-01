package com.example.alpacamusclemaintenance

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.alpacamusclemaintenance.adapters.PageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragmentAdapter = PageAdapter(supportFragmentManager)
        pager.adapter = fragmentAdapter

        tab.setupWithViewPager(pager)
    }
}
