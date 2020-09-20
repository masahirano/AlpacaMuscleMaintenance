// References:
//  - https://github.com/facebook/flipper/blob/6c377ade5cde3c4d2fa398f0a26328363ea8a03f/android/sample/src/debug/java/com/facebook/flipper/sample/FlipperInitializer.java
//  - https://qiita.com/k-tomoyasu/items/2547bcfb5e16fd25e546

package com.example.alpacamusclemaintenance

import android.content.Context
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.BuildConfig
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import okhttp3.OkHttpClient

object FlipperInitializer {

  interface IntializationResult {
    val okHttpClient: OkHttpClient
  }

  fun initFlipperPlugins(
    context: Context,
    client: FlipperClient
  ): IntializationResult {
    // https://fbflipper.com/docs/getting-started/android-native#application-setup
    SoLoader.init(context, false)

    val okHttpClientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(context)) {
      val networkFlipperPlugin = NetworkFlipperPlugin()
      okHttpClientBuilder.addInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))

      client.addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
      client.addPlugin(DatabasesFlipperPlugin(context))
      client.addPlugin(networkFlipperPlugin)
      client.start()
    }

    return object : IntializationResult {
      override val okHttpClient: OkHttpClient
        get() = okHttpClientBuilder.build()
    }
  }
}
