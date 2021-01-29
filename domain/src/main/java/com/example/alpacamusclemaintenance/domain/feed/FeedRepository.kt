package com.example.alpacamusclemaintenance.domain.feed

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface FeedRepository {

    fun getFeedResultStream(query: String): Flow<PagingData<Feed>>
}
