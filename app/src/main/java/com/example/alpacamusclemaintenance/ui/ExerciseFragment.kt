package com.example.alpacamusclemaintenance.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.adapter.ExercisePageAdapter
import com.example.alpacamusclemaintenance.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise, container, false)
        binding.tab.setupWithViewPager(binding.pager)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pager = binding.pager
        pager.adapter = ExercisePageAdapter(fragmentManager!!)
        pager.setCurrentItem(0, false)
    }
}
