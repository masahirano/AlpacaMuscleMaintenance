package com.example.alpacamusclemaintenance.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.adapter.ExercisePageAdapter
import kotlinx.android.synthetic.main.fragment_exercise.view.*

class ExerciseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_exercise, container, false)

        rootView.run {
            pager.adapter = ExercisePageAdapter(fragmentManager!!)
            tab.setupWithViewPager(pager)
        }

        // Inflate the layout for this fragment
        return rootView
    }
}
