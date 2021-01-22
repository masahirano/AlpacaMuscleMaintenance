package com.example.alpacamusclemaintenance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.adapter.FeedAdapter
import com.example.alpacamusclemaintenance.adapter.FeedsLoadStateAdapter
import com.example.alpacamusclemaintenance.databinding.FragmentFeedBinding
import com.example.alpacamusclemaintenance.domain.feed.Feed
import com.example.alpacamusclemaintenance.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by activityViewModels()
    private lateinit var binding: FragmentFeedBinding
    private val adapter = FeedAdapter(
        openFeed = { url: String ->
            findNavController()
                .navigate(
                    FeedFragmentDirections.actionFeedFragmentToWebViewFragment(url = url)
                )
        }
    )

    private var fetchJob: Job? = null

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

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.feed.addItemDecoration(decoration)

        initAdapter()
        fetch()
    }

    private fun initAdapter() {
        binding.feed.adapter = adapter.withLoadStateFooter(FeedsLoadStateAdapter())
        adapter.addLoadStateListener { loadState ->
            // Show loading spinner during initial load or refresh.
            binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
        }
    }

    private fun fetch() {
        fetchJob?.cancel()
        fetchJob = lifecycleScope.launch {
            viewModel.fetchFeed(requireContext().getString(R.string.qiita_tag_muscle_maintenance))
                .collectLatest { data: PagingData<Feed> ->
                    adapter.submitData(data)
                }
        }
    }
}
