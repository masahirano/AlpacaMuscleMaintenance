package com.example.alpacamusclemaintenance.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.databinding.FragmentPushUpBinding
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.entity.PushUp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class PushUpFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPushUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_push_up, container, false)

        binding.button.setOnClickListener { ++binding.count }
        binding.finishButton.setOnClickListener {
            if (binding.count > 0) {
                GlobalScope.launch {
                    val database = AppDatabase.getInstance(context!!)
                    database.pushUpDao().insert(PushUp(0, binding.count))
                    binding.count = 0
                }
            }
        }

        return binding.root
    }
}
