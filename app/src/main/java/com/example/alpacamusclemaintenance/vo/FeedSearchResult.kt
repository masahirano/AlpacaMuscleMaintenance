package com.example.alpacamusclemaintenance.vo

sealed class FeedSearchResult {
    data class Success(val data: List<Feed>) : FeedSearchResult()
    data class Error(val error: Exception) : FeedSearchResult()
}
