package com.example.alpacamusclemaintenance.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.example.alpacamusclemaintenance.vo.Feed
import io.reactivex.Single

class FeedViewModel @ViewModelInject constructor(
  private val repository: FeedRepository
) : ViewModel() {

  val feedObservable: Single<List<Feed>>
    get() = repository.getMuscleMaintenanceFeeds()
}
