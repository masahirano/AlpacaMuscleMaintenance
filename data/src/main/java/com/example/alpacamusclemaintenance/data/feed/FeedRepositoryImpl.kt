package com.example.alpacamusclemaintenance.data.feed

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.alpacamusclemaintenance.domain.feed.Feed
import com.example.alpacamusclemaintenance.domain.feed.FeedRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FeedRepositoryImpl @Inject constructor(
    private val service: QiitaApi
) : FeedRepository {

    override fun getFeedResultStream(query: String): Flow<PagingData<Feed>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            FeedPagingSource(
                service = service,
                query = "tag:$query"
            )
        }
    ).flow

    companion object {
        // Set small value to check if pagination works well
        private const val NETWORK_PAGE_SIZE = 10
    }
}
