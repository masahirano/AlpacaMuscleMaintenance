package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.example.alpacamusclemaintenance.vo.Feed
import javax.inject.Inject

class FeedViewModel @Inject constructor(
  private val repository: FeedRepository
) : ViewModel() {

  val feedObservable: LiveData<List<Feed>>
    get() = repository.getFeedsByTag("筋トレ")
}
