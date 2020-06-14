// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di/ViewModelModule.kt

package com.example.alpacamusclemaintenance.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alpacamusclemaintenance.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

@Suppress("unused")
@InstallIn(ApplicationComponent::class)
@Module
abstract class ViewModelModule {

  @[Binds IntoMap ViewModelKey(HomeViewModel::class)]
  abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

  @[Binds IntoMap ViewModelKey(PushUpViewModel::class)]
  abstract fun bindPushUpViewModel(pushUpViewModel: PushUpViewModel): ViewModel

  @[Binds IntoMap ViewModelKey(RecordViewModel::class)]
  abstract fun bindRecordViewModel(recordViewModel: RecordViewModel): ViewModel

  @[Binds IntoMap ViewModelKey(FeedViewModel::class)]
  abstract fun bindFeedViewModel(feedViewModel: FeedViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(
    factory: AlpacaMuscleMaintenanceViewModelFactory
  ): ViewModelProvider.Factory
}
