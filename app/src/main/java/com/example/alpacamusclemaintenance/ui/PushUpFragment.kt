package com.example.alpacamusclemaintenance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.alpacamusclemaintenance.databinding.FragmentPushUpBinding
import com.example.alpacamusclemaintenance.viewmodel.PushUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

@AndroidEntryPoint
class PushUpFragment : Fragment() {

  private lateinit var binding: FragmentPushUpBinding
  private val viewModel: PushUpViewModel by activityViewModels()
  private val disposable = CompositeDisposable()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentPushUpBinding.inflate(
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

    viewModel
      .count
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { count ->
        binding.count = count
      }
      .addTo(disposable)

    binding.onAddClicked = View.OnClickListener {
      viewModel.add(1)
    }
    binding.onSaveClicked = View.OnClickListener {
      viewModel.save()
    }
  }
}
