package com.example.alpacamusclemaintenance.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.alpacamusclemaintenance.api.QiitaApi
import com.example.alpacamusclemaintenance.vo.Feed
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FeedRepository @Inject constructor(
    private val service: QiitaApi
) {

    fun getFeedResultStream(query: String): Flow<PagingData<Feed>> = Pager(
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
