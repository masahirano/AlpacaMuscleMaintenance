package com.example.alpacamusclemaintenance.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.adapters.ExercisePageAdapter
import kotlinx.android.synthetic.main.fragment_exercise.view.*

class ExerciseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_exercise, container, false)

        val pager = rootView.pager.apply {
            adapter = ExercisePageAdapter(fragmentManager!!)
        }
        rootView.tab.run {
            setupWithViewPager(pager)
        }

        // Inflate the layout for this fragment
        return rootView
    }
}
