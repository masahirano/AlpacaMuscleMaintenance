package com.example.alpacamusclemaintenance.domain.pushup

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetPushUpUseCase @Inject constructor(
    private val pushUpRepository: PushUpRepository
) {

    operator fun invoke(): LiveData<List<PushUp>> = pushUpRepository.getPushUps()
}
