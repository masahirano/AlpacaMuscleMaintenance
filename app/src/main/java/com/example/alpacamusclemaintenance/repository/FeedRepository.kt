package com.example.alpacamusclemaintenance.repository

import com.example.alpacamusclemaintenance.api.QiitaService
import com.example.alpacamusclemaintenance.vo.Feed
import io.reactivex.Single
import javax.inject.Inject

class FeedRepository @Inject constructor(
  private val qiitaService: QiitaService
) {

  fun getFeedsByTag(tag: String): Single<List<Feed>> =
    qiitaService.getFeeds("tag:$tag")
}
