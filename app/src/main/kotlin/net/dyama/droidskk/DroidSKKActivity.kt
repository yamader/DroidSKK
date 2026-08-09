package net.dyama.droidskk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.dyama.droidskk.ui.App
import net.dyama.droidskk.ui.DroidSKKTheme

class DroidSKKActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      DroidSKKTheme {
        App()
      }
    }
  }
}
