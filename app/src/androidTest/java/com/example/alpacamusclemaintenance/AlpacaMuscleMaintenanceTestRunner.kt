// @see: https://github.com/android/architecture-components-samples/blob/17c315a050745c61ae8e79000bc0251305bd148b/GithubBrowserSample/app/src/androidTest/java/com/android/example/github/util/GithubTestRunner.kt
//       https://stackoverflow.com/questions/48099870/disable-dagger-injection-in-tests

package com.example.alpacamusclemaintenance

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class AlpacaMuscleMaintenanceTestRunner : AndroidJUnitRunner() {

  override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
    return super.newApplication(cl, TestApp::class.java.name, context)
  }
}
