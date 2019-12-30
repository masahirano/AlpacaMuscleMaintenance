package com.example.alpacamusclemaintenance.api

import com.example.alpacamusclemaintenance.vo.Feed
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaService {

  @GET("api/v2/items")
  fun getFeeds(@Query("query") query: String): Single<List<Feed>>

  companion object {
    const val HTTPS_API_QIITA_URL = "https://qiita.com"
  }
}
