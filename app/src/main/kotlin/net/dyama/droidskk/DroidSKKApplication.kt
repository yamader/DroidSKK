package net.dyama.droidskk

import android.app.Application
import android.content.Context

val Context.appContainer get() = (applicationContext as DroidSKKApplication).container

class DroidSKKApplication : Application() {
  lateinit var container: AppContainer private set

  override fun onCreate() {
    super.onCreate()
    container = AppContainer(this)
  }
}
