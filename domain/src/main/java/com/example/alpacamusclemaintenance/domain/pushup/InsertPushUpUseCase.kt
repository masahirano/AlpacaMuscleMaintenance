package com.example.alpacamusclemaintenance.domain.pushup

import javax.inject.Inject

class InsertPushUpUseCase @Inject constructor(
    private val pushUpRepository: PushUpRepository
) {

    suspend operator fun invoke(pushUp: PushUp) {
        pushUpRepository.insert(pushUp)
    }
}
