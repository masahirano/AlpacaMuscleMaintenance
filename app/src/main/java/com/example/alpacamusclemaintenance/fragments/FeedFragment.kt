package com.example.alpacamusclemaintenance.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.adapters.FeedAdapter
import com.example.alpacamusclemaintenance.databinding.FragmentFeedBinding
import com.example.alpacamusclemaintenance.viewmodel.FeedViewModel
import com.example.alpacamusclemaintenance.vo.Feed


/**
 * A simple [Fragment] subclass.
 *
 */
class FeedFragment : Fragment() {
    private val feedAdapter: FeedAdapter = FeedAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        val rootView = inflater.inflate(R.layout.fragment_feed, container, false)
//FragmentFeedBinding
        val binding: FragmentFeedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false);
        binding.feed.adapter = feedAdapter
//        binding.isLoading = false

//        val feeds = FeedRepository.getInstance().getFeedsByTag("筋トレ")
//        adapter.setFeedList(feeds)
//        val url = "https://qiita.com/api/v2/items?query=tag:筋トレ"
//        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
//                Response.Listener { response ->
//                    System.out.println("successssssssssssss")
//                },
//                Response.ErrorListener { error ->
//                    System.out.println("errorrrrrrrrrrrrrrr")
//                }
//        )
//
//        // Access the RequestQueue through your singleton class.
//        VolleyRequestQueueManager.getInstance(context!!).addToRequestQueue(jsonArrayRequest)

        return binding.root
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: FeedViewModel) {
        // Update the list when the data changes
        viewModel.feedObservable.observe(this, Observer<List<Feed>> {
            feeds -> feedAdapter.setFeedList(feeds!!)
        })
    }
}
