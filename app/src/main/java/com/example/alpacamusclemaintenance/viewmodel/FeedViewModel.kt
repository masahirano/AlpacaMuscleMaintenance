package com.example.alpacamusclemaintenance.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.example.alpacamusclemaintenance.vo.Feed
import kotlinx.coroutines.flow.Flow

class FeedViewModel @ViewModelInject constructor(
  private val repository: FeedRepository
) : ViewModel() {

  private var currentSearchResult: Flow<PagingData<Feed>>? = null

  fun fetchFeed(): Flow<PagingData<Feed>> {
    val newResult: Flow<PagingData<Feed>> = repository.getFeedResultStream()
      .cachedIn(viewModelScope)
    currentSearchResult = newResult
    return newResult
  }
}
