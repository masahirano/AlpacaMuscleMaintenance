package com.example.alpacamusclemaintenance.data.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alpacamusclemaintenance.domain.home.Home
import com.example.alpacamusclemaintenance.domain.home.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {

    override fun getHome(): LiveData<Home> {
        // TODO: They should be stored DB and queried with Room
        val words = listOf(
            Home(
                "Strength does not come from the physical capacity." +
                    " It comes from an indomitable will.",
                "Ghandi"
            ),
            Home("The best way to predict the future is to create it.", "Abraham Lincoln"),
            Home("Work hard in silence. Let success be your noise.", "Frank Ocean"),
            Home("The difference between try and triumph is a little umph.", "Marvin Phillips")
        )

        return MutableLiveData(words.shuffled().first())
    }
}
