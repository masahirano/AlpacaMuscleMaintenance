package com.example.alpacamusclemaintenance.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.api.QiitaService
import com.example.alpacamusclemaintenance.vo.Feed
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FeedRepository @Inject constructor(
  private val service: QiitaService,
  private val context: Context
) {

  fun getFeedResultStream(): Flow<PagingData<Feed>> {
    return Pager(
      config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
      pagingSourceFactory = {
        FeedPagingSource(
          service = service,
          query = "tag:${context.getString(R.string.qiita_tag_muscle_maintenance)}"
        )
      }
    ).flow
  }

  companion object {
    // Set small value to check if pagination works well
    private const val NETWORK_PAGE_SIZE = 10
  }
}
