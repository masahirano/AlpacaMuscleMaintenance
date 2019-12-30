package com.example.alpacamusclemaintenance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.alpacamusclemaintenance.adapter.FeedAdapter
import com.example.alpacamusclemaintenance.databinding.FragmentFeedBinding
import com.example.alpacamusclemaintenance.di.Injectable
import com.example.alpacamusclemaintenance.viewmodel.FeedViewModel
import com.example.alpacamusclemaintenance.vo.Feed
import javax.inject.Inject

class FeedFragment : Fragment(), Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var feedViewModel: FeedViewModel
  private lateinit var binding: FragmentFeedBinding

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
              .actionFeedFragmentToWebViewFragment(
                url = url
              )
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
    feedViewModel =
      ViewModelProviders
        .of(this, viewModelFactory)
        .get(FeedViewModel::class.java)
        .apply {
          feedObservable.observe(this@FeedFragment, Observer<List<Feed>> { feeds ->
            binding.progressBar.visibility = View.GONE
            feedAdapter.setFeedList(feeds)
          })
        }
  }
}
