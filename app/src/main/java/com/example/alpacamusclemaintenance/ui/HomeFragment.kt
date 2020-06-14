package com.example.alpacamusclemaintenance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.alpacamusclemaintenance.databinding.FragmentHomeBinding
import com.example.alpacamusclemaintenance.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private val homeViewModel: HomeViewModel by activityViewModels()
  private lateinit var binding: FragmentHomeBinding
  private val disposable = CompositeDisposable()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentHomeBinding.inflate(
      inflater,
      container,
      false
    )
    return binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    AnimationUtils
      .loadAnimation(context, android.R.anim.fade_in)
      .apply { duration = 2_000 }
      .also {
        binding.wordOfWisdom.animation = it
        binding.author.animation = it
      }

    homeViewModel
      .data
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { home ->
        binding.home = home
      }
      .addTo(disposable)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposable.clear()
  }
}
