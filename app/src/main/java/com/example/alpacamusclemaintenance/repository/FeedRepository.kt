package com.example.alpacamusclemaintenance.repository

import com.example.alpacamusclemaintenance.api.QiitaService
import com.example.alpacamusclemaintenance.vo.Feed
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class FeedRepository @Inject constructor() {

  private val qiitaService: QiitaService

  init {
    val gson = GsonBuilder()
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .create()
    val retrofit = Retrofit
      .Builder()
      .baseUrl(QiitaService.HTTPS_API_QIITA_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
    qiitaService = retrofit.create(QiitaService::class.java)
  }

  fun getFeedsByTag(tag: String): Single<List<Feed>> =
    qiitaService.getFeeds("tag:$tag")
}
