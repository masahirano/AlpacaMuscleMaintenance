// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/GithubApp.kt

package com.example.alpacamusclemaintenance

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AlpacaMuscleMaintenanceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // @see https://github.com/facebook/flipper/blob/6c377ade5cde3c4d2fa398f0a26328363ea8a03f/android/sample/src/main/java/com/facebook/flipper/sample/FlipperSampleApplication.java
        // At this moment I simply ignore result value since I don't have NetWorkClient like
        // https://github.com/facebook/flipper/blob/6c377ade5cde3c4d2fa398f0a26328363ea8a03f/android/sample/src/main/java/com/facebook/flipper/sample/network/NetworkClient.java
        // but will add later if I need one.
        FlipperInitializer.initFlipperPlugins(context = this)
    }
}
