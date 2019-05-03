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
import androidx.lifecycle.ViewModelProvider
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.adapter.FeedAdapter
import com.example.alpacamusclemaintenance.databinding.FragmentFeedBinding
import com.example.alpacamusclemaintenance.di.Injectable
import com.example.alpacamusclemaintenance.viewmodel.FeedViewModel
import com.example.alpacamusclemaintenance.vo.Feed
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class FeedFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var feedViewModel: FeedViewModel

    private lateinit var feedAdapter: FeedAdapter
    private lateinit var binding: FragmentFeedBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        feedAdapter = FeedAdapter()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        binding.feed.let { feedView ->
            feedView.adapter = feedAdapter
            val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            feedView.addItemDecoration(dividerItemDecoration)
        }

        return binding.root
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        feedViewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)
        feedViewModel.feedObservable.observe(this, Observer<List<Feed>> { feeds ->
            feeds?.let {
                binding.progressBar.visibility = View.GONE
                feedAdapter.setFeedList(it)
            }
        })
    }
}
