package com.example.alpacamusclemaintenance.domain.feed

import androidx.paging.PagingData
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FetchFeedResultUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {

    operator fun invoke(query: String): Flow<PagingData<Feed>> = feedRepository
        .getFeedResultStream(query)
}
