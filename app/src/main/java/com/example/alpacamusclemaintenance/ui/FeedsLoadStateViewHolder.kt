package com.example.alpacamusclemaintenance.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.alpacamusclemaintenance.databinding.FeedsLoadStateFooterViewItemBinding

class FeedsLoadStateViewHolder(
  private val binding: FeedsLoadStateFooterViewItemBinding
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(loadState: LoadState) {
    binding.progressBar.isVisible = loadState is LoadState.Loading
  }

  companion object {
    fun create(parent: ViewGroup): FeedsLoadStateViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding = FeedsLoadStateFooterViewItemBinding.inflate(inflater, parent, false)

      return FeedsLoadStateViewHolder(binding)
    }
  }
}
