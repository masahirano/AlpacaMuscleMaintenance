package com.example.alpacamusclemaintenance.api

import com.example.alpacamusclemaintenance.vo.Feed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaService {
    @GET("api/v2/items")
    fun getFeeds(@Query("query") query: String): Call<List<Feed>>

    companion object {
        const val HTTPS_API_QIITA_URL = "https://qiita.com/"
    }
}