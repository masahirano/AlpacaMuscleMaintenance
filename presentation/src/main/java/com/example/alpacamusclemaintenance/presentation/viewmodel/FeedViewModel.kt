package com.example.alpacamusclemaintenance.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.alpacamusclemaintenance.data.feed.FeedRepository
import com.example.alpacamusclemaintenance.domain.feed.Feed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: FeedRepository
) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<Feed>>? = null

    fun fetchFeed(query: String): Flow<PagingData<Feed>> {
        val newResult: Flow<PagingData<Feed>> = repository.getFeedResultStream(query)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}
