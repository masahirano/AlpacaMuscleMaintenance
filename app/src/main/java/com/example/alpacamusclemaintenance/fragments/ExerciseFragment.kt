package com.example.alpacamusclemaintenance.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.adapters.ExercisePageAdapter

class ExerciseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_exercise, container, false)

        val pager = rootView.findViewById<ViewPager>(R.id.pager).apply {
            adapter = ExercisePageAdapter(fragmentManager!!)
        }
        rootView.findViewById<TabLayout>(R.id.tab).run {
            setupWithViewPager(pager)
        }

        // Inflate the layout for this fragment
        return rootView
    }
}
