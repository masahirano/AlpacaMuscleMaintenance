package com.example.alpacamusclemaintenance.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.alpacamusclemaintenance.MainActivity
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.databinding.ListItemFeedBinding
import com.example.alpacamusclemaintenance.ui.WebViewFragment
import com.example.alpacamusclemaintenance.vo.Feed

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

  private lateinit var binding: ListItemFeedBinding
  private var feedList: List<Feed> = emptyList()

  fun setFeedList(feedList: List<Feed>) {
    this.feedList = feedList
    notifyItemRangeInserted(0, feedList.size)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
    binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_feed, parent, false)

    return FeedViewHolder(binding)
  }

  override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
    val context = binding.root.context
    val feed = feedList[position]

    Glide.with(context)
      .load(feed.user.profileImageUrl)
      .transition(DrawableTransitionOptions.withCrossFade())
      .into(holder.binding.profileImage)

    holder.binding.run {
      this.feed = feed
      executePendingBindings()
    }

    holder.itemView.setOnClickListener {
      (context as MainActivity).supportFragmentManager
        .beginTransaction()
        .replace(R.id.feed_container, WebViewFragment.newInstance(feed.url))
        .addToBackStack(null)
        .commit()
    }
  }

  override fun getItemCount(): Int = feedList.size

  class FeedViewHolder(val binding: ListItemFeedBinding) : RecyclerView.ViewHolder(binding.root)
}
