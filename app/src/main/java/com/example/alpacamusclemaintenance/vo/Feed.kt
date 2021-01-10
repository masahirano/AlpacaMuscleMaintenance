package com.example.alpacamusclemaintenance.vo

import java.util.Date

data class Feed(
    val user: User,
    val body: String,
    val createdAt: Date,
    val likesCount: Int,
    val title: String,
    val url: String
)

data class User(
    val id: String,
    val profileImageUrl: String
)
