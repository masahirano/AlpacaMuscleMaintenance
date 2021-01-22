package com.example.alpacamusclemaintenance.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.alpacamusclemaintenance.domain.feed.Feed
import com.example.alpacamusclemaintenance.presentation.databinding.ListItemFeedBinding

class FeedAdapter(
    private val openFeed: FeedOpenNavigation
) : PagingDataAdapter<Feed, FeedViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedViewHolder = ListItemFeedBinding
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
        val selectedFeed = getItem(position) ?: return
        val binding = holder.binding
        Glide.with(binding.root.context)
            .load(selectedFeed.user.profileImageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.profileImage)
        binding.apply {
            feed = selectedFeed
            onClicked = View.OnClickListener { openFeed(selectedFeed.url) }
            executePendingBindings()
        }
    }

    companion object {

        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Feed>() {
            override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean =
                oldItem == newItem
        }
    }
}

class FeedViewHolder(val binding: ListItemFeedBinding) : RecyclerView.ViewHolder(binding.root)

typealias FeedOpenNavigation = (url: String) -> Unit
