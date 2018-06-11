package com.example.alpacamusclemaintenance.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.Nullable
import com.example.alpacamusclemaintenance.api.QiitaService
import com.example.alpacamusclemaintenance.vo.Feed
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Repository that handles Repo instances.
 *
 * unfortunate naming :/ .
 * Repo - value object name
 * Repository - type of this class.
 */
class FeedRepository private constructor() {

    private var qiitaService: QiitaService

    init {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl(QiitaService.HTTPS_API_QIITA_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        qiitaService = retrofit.create(QiitaService::class.java)
    }

    fun getFeedsByTag(tag: String): LiveData<List<Feed>> {
        val data = MutableLiveData<List<Feed>>()

        qiitaService.getFeeds("tag:$tag").enqueue(object : Callback<List<Feed>> {
            override fun onResponse(call: Call<List<Feed>>, @Nullable response: Response<List<Feed>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Feed>>, t: Throwable) {
                // TODO: handle error
                error(t)
            }
        })

        return data
    }

    companion object {
        private var INSTANCE: FeedRepository? = null
        fun getInstance() =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: FeedRepository()
                }
    }
}
