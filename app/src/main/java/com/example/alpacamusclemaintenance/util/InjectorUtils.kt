package com.example.alpacamusclemaintenance.util

import android.content.Context
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.repository.PushUpRepository
import com.example.alpacamusclemaintenance.viewmodel.PushUpViewModelFactory

object InjectorUtils {

    private fun getPushUpRepository(context: Context): PushUpRepository {
        return PushUpRepository.getInstance(AppDatabase.getInstance(context).pushUpDao())
    }

    fun providePushUpViewModelFactory(context: Context): PushUpViewModelFactory{
        val repository = getPushUpRepository(context)
        return PushUpViewModelFactory(repository)
    }

}
