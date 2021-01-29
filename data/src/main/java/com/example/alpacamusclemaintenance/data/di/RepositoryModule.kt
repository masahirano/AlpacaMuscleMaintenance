package com.example.alpacamusclemaintenance.data.di

import com.example.alpacamusclemaintenance.data.feed.FeedRepositoryImpl
import com.example.alpacamusclemaintenance.data.home.HomeRepositoryImpl
import com.example.alpacamusclemaintenance.domain.feed.FeedRepository
import com.example.alpacamusclemaintenance.domain.home.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Singleton
    @Binds
    abstract fun bindFeedRepository(
        feedRepositoryImpl: FeedRepositoryImpl
    ): FeedRepository
}
