package com.example.alpacamusclemaintenance.repository

import android.content.Context
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.api.QiitaService
import com.example.alpacamusclemaintenance.vo.Feed
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FeedRepository @Inject constructor(
  private val qiitaService: QiitaService,
  private val context: Context
) {

  fun getMuscleMaintenanceFeeds(): Single<List<Feed>> =
    getFeedsByTag(context.getString(R.string.qiita_tag_muscle_maintenance))
      .subscribeOn(Schedulers.io())

  private fun getFeedsByTag(tag: String): Single<List<Feed>> =
    qiitaService.getFeeds("tag:$tag")
}
