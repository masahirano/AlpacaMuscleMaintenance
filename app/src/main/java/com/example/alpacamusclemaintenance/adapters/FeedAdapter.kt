package com.example.alpacamusclemaintenance.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.databinding.ListItemFeedBinding
import com.example.alpacamusclemaintenance.vo.Feed

class FeedAdapter(val context: Context) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private var feedList: List<Feed>? = null

    fun setFeedList(feedList: List<Feed>) {
        this.feedList = feedList
        notifyItemRangeInserted(0, feedList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding: ListItemFeedBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.list_item_feed, parent, false
        )

        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = feedList!![position]
        holder.apply {
            Glide.with(context)
                    .load(feed.user.profileImageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.profileImage)
            binding.feed = feed
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return if (feedList == null) 0 else feedList!!.size
    }

    class FeedViewHolder(val binding: ListItemFeedBinding) : RecyclerView.ViewHolder(binding.root)
}
