package com.example.alpacamusclemaintenance.presentation.excercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alpacamusclemaintenance.presentation.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        binding.tab.setupWithViewPager(binding.pager)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.pager.adapter = ExercisePageAdapter(childFragmentManager)
        binding.pager.setCurrentItem(0, false)
    }
}
