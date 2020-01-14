package com.example.alpacamusclemaintenance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.alpacamusclemaintenance.databinding.FragmentHomeBinding
import com.example.alpacamusclemaintenance.di.Injectable
import com.example.alpacamusclemaintenance.viewmodel.HomeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var homeViewModel: HomeViewModel
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

    homeViewModel =
      ViewModelProviders
        .of(this, viewModelFactory)
        .get(HomeViewModel::class.java)
    homeViewModel
      .data
      .subscribeOn(Schedulers.io())
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
