package com.example.alpacamusclemaintenance.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alpacamusclemaintenance.domain.home.GetHomeUseCase
import com.example.alpacamusclemaintenance.domain.home.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase
) : ViewModel() {

    val data: LiveData<Home>
        get() = getHomeUseCase()
}
