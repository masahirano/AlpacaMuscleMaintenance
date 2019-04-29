package com.example.alpacamusclemaintenance.util

import android.content.Context
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.repository.FeedRepository
import com.example.alpacamusclemaintenance.repository.PushUpRepository
import com.example.alpacamusclemaintenance.viewmodel.FeedViewModelFactory
import com.example.alpacamusclemaintenance.viewmodel.PushUpViewModelFactory

object InjectorUtils {

//    private fun getPushUpRepository(context: Context): PushUpRepository {
//        return PushUpRepository.getInstance(AppDatabase.getInstance(context).pushUpDao())
//    }

    private fun getFeedRepository(): FeedRepository {
        return FeedRepository.getInstance()
    }

//    fun providePushUpViewModelFactory(context: Context): PushUpViewModelFactory {
//        val repository = getPushUpRepository(context)
//        return PushUpViewModelFactory(repository)
//    }

    fun provideFeedViewModelFactory() : FeedViewModelFactory {
        val repository = getFeedRepository()
        return FeedViewModelFactory(repository)
    }

}
