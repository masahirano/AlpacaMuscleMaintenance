package com.example.alpacamusclemaintenance.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.databinding.FragmentHomeBinding
import com.example.alpacamusclemaintenance.util.InjectorUtils
import com.example.alpacamusclemaintenance.viewmodel.HomeViewModel
import com.example.alpacamusclemaintenance.vo.Home

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        AnimationUtils.loadAnimation(context, android.R.anim.fade_in).also {
            it.duration = 2_000
            binding.wordOfWisdom.animation = it
            binding.author.animation = it
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = InjectorUtils.provideHomeViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: HomeViewModel) {
        viewModel.homeObservable.observe(this, Observer<Home> { home ->
            home?.let {
                binding.home = home
            }
        })
    }
}
