package com.example.alpacamusclemaintenance.presentation.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.alpacamusclemaintenance.presentation.ui.FeedsLoadStateViewHolder

class FeedsLoadStateAdapter : LoadStateAdapter<FeedsLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: FeedsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): FeedsLoadStateViewHolder = FeedsLoadStateViewHolder.create(parent)
}
