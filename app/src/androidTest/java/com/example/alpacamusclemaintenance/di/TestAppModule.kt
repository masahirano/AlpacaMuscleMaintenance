package com.example.alpacamusclemaintenance.di

import com.example.alpacamusclemaintenance.api.QiitaService
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.dao.PushUpDao
import com.example.alpacamusclemaintenance.repository.FeedRepository
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

  @Provides
  fun provideFeedRepository(): FeedRepository = mock(FeedRepository::class.java)

  @Provides
  fun provideQiitaService(): QiitaService = mock(QiitaService::class.java)
}
