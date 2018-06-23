package com.example.alpacamusclemaintenance.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.databinding.FragmentPushUpBinding
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.entity.PushUp
import kotlinx.coroutines.experimental.launch

/**
 * A simple [Fragment] subclass.
 */
class PushUpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPushUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_push_up, container, false)

        binding.button.setOnClickListener { ++binding.count }
        binding.finishButton.setOnClickListener {
            launch {
                val database = AppDatabase.getInstance(context!!)
                database.pushUpDao().insert(PushUp(0, binding.count))
                binding.count = 0
            }
        }

        return binding.root
    }
}
