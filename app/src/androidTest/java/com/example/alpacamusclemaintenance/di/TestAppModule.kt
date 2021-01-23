package com.example.alpacamusclemaintenance.di

import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.mockito.Mockito.mock

@InstallIn(ApplicationComponent::class)
@Module
class TestAppModule {

    @Provides
    fun provideAppDatabase(): AppDatabase = mock(AppDatabase::class.java)

    @Provides
    fun providePushUpDao(): PushUpDao = mock(PushUpDao::class.java)
}
