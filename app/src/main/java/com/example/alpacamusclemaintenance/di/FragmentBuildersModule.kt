// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di/FragmentBuildersModule.kt

package com.example.alpacamusclemaintenance.di

import com.example.alpacamusclemaintenance.ui.FeedFragment
import com.example.alpacamusclemaintenance.ui.HomeFragment
import com.example.alpacamusclemaintenance.ui.RecordFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeRecordFragment(): RecordFragment

    @ContributesAndroidInjector
    abstract fun contributeFeedFragment(): FeedFragment
}
