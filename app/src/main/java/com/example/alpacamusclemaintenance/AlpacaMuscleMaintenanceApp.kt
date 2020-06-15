// From: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/GithubApp.kt

package com.example.alpacamusclemaintenance

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AlpacaMuscleMaintenanceApp : Application() {

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    // https://fbflipper.com/docs/getting-started.html#setup-your-android-app
    SoLoader.init(this, false)
    if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
      val client = AndroidFlipperClient.getInstance(this)
      client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
      client.addPlugin(DatabasesFlipperPlugin(this))
      client.start()
    }
  }
}
