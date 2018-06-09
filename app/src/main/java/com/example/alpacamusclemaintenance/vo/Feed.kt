package com.example.alpacamusclemaintenance.vo

import com.google.gson.annotations.SerializedName

data class Feed(
        @field:SerializedName("body")
        var body: String,
        @field:SerializedName("created_at")
        var createdAt: String,
        @field:SerializedName("likes_count")
        var likesCount: Int,
        @field:SerializedName("title")
        var title: String,
        @field:SerializedName("url")
        var url: String
)

