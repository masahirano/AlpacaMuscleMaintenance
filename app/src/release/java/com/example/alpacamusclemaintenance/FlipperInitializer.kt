// References:
//  - https://github.com/facebook/flipper/blob/6c377ade5cde3c4d2fa398f0a26328363ea8a03f/android/sample/src/release/java/com/facebook/flipper/sample/FlipperInitializer.java
// See also "src/debug/java/com/example/alpacamusclemaintenance/FlipperInitializer.kt"

package com.example.alpacamusclemaintenance

import android.content.Context
import com.facebook.flipper.core.FlipperClient
import okhttp3.OkHttpClient

object FlipperInitializer {

  interface IntializationResult {
    val okHttpClient: OkHttpClient
  }

  fun initFlipperPlugins(context: Context): IntializationResult {
    return object : IntializationResult {
      override val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder().build()
    }
  }
}
