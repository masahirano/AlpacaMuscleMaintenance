package com.example.alpacamusclemaintenance.vo

import java.util.*

data class User(
        var id: String,
        var profileImageUrl: String
)

data class Feed(
        var user: User,
        var body: String,
        var createdAt: Date,
        var likesCount: Int,
        var title: String,
        var url: String
)

