package com.example.alpacamusclemaintenance.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.databinding.ListItemFeedBinding
import com.example.alpacamusclemaintenance.vo.Feed

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private var feedList: List<Feed>? = null

    fun setFeedList(feedList: List<Feed>) {
        this.feedList = feedList
        notifyItemRangeInserted(0, feedList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding: ListItemFeedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_feed, parent, false)

        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
//        holder.binding.setProject(feedList!![position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (feedList == null) 0 else feedList!!.size
    }

    class FeedViewHolder(val binding: ListItemFeedBinding) : RecyclerView.ViewHolder(binding.root)
}
