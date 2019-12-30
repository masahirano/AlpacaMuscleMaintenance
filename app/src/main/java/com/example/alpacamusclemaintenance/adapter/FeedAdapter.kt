package com.example.alpacamusclemaintenance.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.alpacamusclemaintenance.databinding.ListItemFeedBinding
import com.example.alpacamusclemaintenance.vo.Feed

class FeedAdapter(
  private val openFeed: FeedOpenNavigation
) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

  private var feedList: List<Feed> = emptyList()

  fun setFeedList(feedList: List<Feed>) {
    this.feedList = feedList
    notifyItemRangeInserted(0, feedList.size)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): FeedViewHolder =
    ListItemFeedBinding
      .inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
      .let { FeedViewHolder(it) }

  override fun onBindViewHolder(
    holder: FeedViewHolder,
    position: Int
  ) {
    val binding = holder.binding
    val selectedFeed = feedList[position]
    Glide
      .with(binding.root.context)
      .load(selectedFeed.user.profileImageUrl)
      .transition(DrawableTransitionOptions.withCrossFade())
      .into(binding.profileImage)
    binding
      .apply {
        feed = selectedFeed
        onClicked = View.OnClickListener { openFeed(selectedFeed.url) }
        executePendingBindings()
      }
  }

  override fun getItemCount(): Int = feedList.size

  class FeedViewHolder(val binding: ListItemFeedBinding) : RecyclerView.ViewHolder(binding.root)
}

typealias FeedOpenNavigation = (url: String) -> Unit

