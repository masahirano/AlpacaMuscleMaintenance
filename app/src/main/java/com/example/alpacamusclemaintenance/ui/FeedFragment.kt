package com.example.alpacamusclemaintenance.ui


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.adapter.FeedAdapter
import com.example.alpacamusclemaintenance.databinding.FragmentFeedBinding
import com.example.alpacamusclemaintenance.util.InjectorUtils
import com.example.alpacamusclemaintenance.viewmodel.FeedViewModel
import com.example.alpacamusclemaintenance.vo.Feed


/**
 * A simple [Fragment] subclass.
 *
 */
class FeedFragment : androidx.fragment.app.Fragment() {

    private lateinit var feedAdapter: FeedAdapter
    private lateinit var binding: FragmentFeedBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        feedAdapter = FeedAdapter()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        binding.feed.let { feedView ->
            feedView.adapter = feedAdapter

            val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(context, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL)
            feedView.addItemDecoration(dividerItemDecoration)
        }

        return binding.root
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = InjectorUtils.provideFeedViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(FeedViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: FeedViewModel) {
        // Update the list when the data changes
        viewModel.feedObservable.observe(this, Observer<List<Feed>> { feeds ->
            feeds?.let {
                binding.progressBar.visibility = View.GONE
                feedAdapter.setFeedList(it)
            }
        })
    }
}
