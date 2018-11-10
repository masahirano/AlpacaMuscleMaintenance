package com.example.alpacamusclemaintenance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.example.alpacamusclemaintenance.vo.Feed


class FeedViewModel internal constructor(
        private val repository: FeedRepository
): ViewModel() {

    var feedObservable: LiveData<List<Feed>> = repository.getFeedsByTag("筋トレ")

}
