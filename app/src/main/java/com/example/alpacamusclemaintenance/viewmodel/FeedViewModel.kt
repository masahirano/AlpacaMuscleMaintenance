package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.example.alpacamusclemaintenance.vo.Feed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class FeedViewModel @Inject constructor(
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
