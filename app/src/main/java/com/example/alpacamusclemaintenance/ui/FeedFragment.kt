package com.example.alpacamusclemaintenance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.alpacamusclemaintenance.adapter.FeedAdapter
import com.example.alpacamusclemaintenance.databinding.FragmentFeedBinding
import com.example.alpacamusclemaintenance.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

@AndroidEntryPoint
class FeedFragment : Fragment() {

  private val feedViewModel: FeedViewModel by activityViewModels()
  private lateinit var binding: FragmentFeedBinding
  private val disposable = CompositeDisposable()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentFeedBinding.inflate(
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

    val feedAdapter = FeedAdapter(
      openFeed = { url: String ->
        findNavController()
          .navigate(
            FeedFragmentDirections
              .actionFeedFragmentToWebViewFragment(url = url)
          )
      }
    )

    binding.feed.apply {
      adapter = feedAdapter
      addItemDecoration(
        DividerItemDecoration(
          context,
          DividerItemDecoration.VERTICAL
        )
      )
    }

    feedViewModel
      .feedObservable
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(
        onError = { e ->
          // TODO: handle error
          error(e)
        },
        onSuccess = { feeds ->
          binding.progressBar.visibility = View.GONE
          feedAdapter.setFeedList(feeds)
        }
      )
      .addTo(disposable)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposable.clear()
  }
}
