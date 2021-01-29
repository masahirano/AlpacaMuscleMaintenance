package com.example.alpacamusclemaintenance.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.alpacamusclemaintenance.domain.feed.Feed
import com.example.alpacamusclemaintenance.domain.feed.FetchFeedResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val fetchFeedResultUseCase: FetchFeedResultUseCase
) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<Feed>>? = null

    fun fetchFeed(query: String): Flow<PagingData<Feed>> = fetchFeedResultUseCase(query)
        .cachedIn(viewModelScope)
        .also { currentSearchResult = it }
}
