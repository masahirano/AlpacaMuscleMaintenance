package com.example.alpacamusclemaintenance.domain.home

import androidx.lifecycle.LiveData

interface HomeRepository {

    fun getHome(): LiveData<Home>
}
