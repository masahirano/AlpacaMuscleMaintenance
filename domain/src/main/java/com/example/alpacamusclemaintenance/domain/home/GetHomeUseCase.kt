package com.example.alpacamusclemaintenance.domain.home

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    operator fun invoke(): LiveData<Home> = homeRepository.getHome()
}
