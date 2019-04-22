package com.example.alpacamusclemaintenance.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alpacamusclemaintenance.vo.Home

class HomeRepository private constructor() {

    fun getHome(): LiveData<Home> {
        val data = MutableLiveData<Home>()
        val words = arrayListOf(
                Home("Strength does not come from the physical capacity. It comes from an indomitable will.", "Ghandi"),
                Home("The best way to predict the future is to create it.", "Abraham Lincoln"),
                Home("Work hard in silence. Let success be your noise.", "Frank Ocean"),
                Home("The difference between try and triumph is a little umph.", "Marvin Phillips")
        )
        data.value = words.shuffled().first()

        return data
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: HomeRepository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: HomeRepository().also { instance = it }
                }
    }

}
