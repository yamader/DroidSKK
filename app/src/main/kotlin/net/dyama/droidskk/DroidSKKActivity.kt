package net.dyama.droidskk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import net.dyama.droidskk.app.App
import net.dyama.droidskk.lib.theme.DroidSKKTheme

class DroidSKKActivity: ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      DroidSKKTheme {
        App()
      }
    }
  }
}
