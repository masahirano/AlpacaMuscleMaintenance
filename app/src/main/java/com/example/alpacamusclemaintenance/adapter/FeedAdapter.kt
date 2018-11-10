package com.example.alpacamusclemaintenance.adapter

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.alpacamusclemaintenance.MainActivity
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.databinding.ListItemFeedBinding
import com.example.alpacamusclemaintenance.ui.WebViewFragment
import com.example.alpacamusclemaintenance.vo.Feed

class FeedAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private lateinit var context: Context
    private var feedList: List<Feed>? = null

    fun setFeedList(feedList: List<Feed>) {
        this.feedList = feedList
        notifyItemRangeInserted(0, feedList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        context = parent.context
        val binding: ListItemFeedBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.list_item_feed, parent, false
        )

        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = feedList!![position]

        Glide.with(context)
                .load(feed.user.profileImageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.binding.profileImage)

        holder.binding.run {
            this.feed = feed
            executePendingBindings()
        }

        holder.itemView.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().run {
                replace(R.id.content, WebViewFragment.newInstance(feed.url))
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun getItemCount(): Int {
        return if (feedList == null) 0 else feedList!!.size
    }

    class FeedViewHolder(val binding: ListItemFeedBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)
}
