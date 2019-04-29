package com.example.alpacamusclemaintenance.util

import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.example.alpacamusclemaintenance.viewmodel.FeedViewModelFactory

object InjectorUtils {

    private fun getFeedRepository(): FeedRepository {
        return FeedRepository.getInstance()
    }

    fun provideFeedViewModelFactory() : FeedViewModelFactory {
        val repository = getFeedRepository()
        return FeedViewModelFactory(repository)
    }

}
