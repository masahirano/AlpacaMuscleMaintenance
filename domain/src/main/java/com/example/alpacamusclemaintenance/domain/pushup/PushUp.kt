package com.example.alpacamusclemaintenance.domain.pushup

import java.util.Date

data class PushUp(
    val id: Int,
    val count: Int,
    val doneAt: Date,
    val createdAt: Date,
    val updatedAt: Date
)
