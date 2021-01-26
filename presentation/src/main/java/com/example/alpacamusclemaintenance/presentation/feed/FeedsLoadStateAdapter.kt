package com.example.alpacamusclemaintenance.presentation.feed

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class FeedsLoadStateAdapter : LoadStateAdapter<FeedsLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: FeedsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): FeedsLoadStateViewHolder = FeedsLoadStateViewHolder.create(parent)
}
