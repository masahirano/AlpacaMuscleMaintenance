package com.example.alpacamusclemaintenance.di

import com.example.alpacamusclemaintenance.data.feed.QiitaApi
import com.example.alpacamusclemaintenance.data.pushup.PushUpRepository
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mockito.Mockito.mock

@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    @Provides
    fun provideAppDatabase(): AppDatabase = mock(AppDatabase::class.java)

    @Provides
    fun providePushUpDao(): PushUpDao = mock(PushUpDao::class.java)
}
