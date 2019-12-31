package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.example.alpacamusclemaintenance.vo.Feed
import io.reactivex.Single
import javax.inject.Inject

class FeedViewModel @Inject constructor(
  private val repository: FeedRepository
) : ViewModel() {

  val feedObservable: Single<List<Feed>>
    get() = repository.getFeedsByTag("筋トレ")
}
