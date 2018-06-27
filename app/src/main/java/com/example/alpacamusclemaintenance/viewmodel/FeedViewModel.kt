package com.example.alpacamusclemaintenance.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.example.alpacamusclemaintenance.vo.Feed


class FeedViewModel internal constructor(
        private val repository: FeedRepository
): ViewModel() {

    var feedObservable: LiveData<List<Feed>> = repository.getFeedsByTag("筋トレ")

}
