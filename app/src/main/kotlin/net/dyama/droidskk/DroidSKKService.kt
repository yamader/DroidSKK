package net.dyama.droidskk

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.View
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.ui.platform.ComposeView
import net.dyama.droidskk.lib.LifecycleInputMethodService
import net.dyama.droidskk.ui.DroidSKKTheme
import net.dyama.droidskk.ui.Keyboard

val Context.serviceContainer get() = (this as DroidSKKService).container
val Context.currentInputConnection get() = (this as InputMethodService).currentInputConnection

class DroidSKKService : LifecycleInputMethodService() {
  lateinit var container: ServiceContainer private set

  override fun onCreate() {
    super.onCreate()
    container = ServiceContainer(this)
  }

  override fun onCreateInputView(): View {
    installLifecycle()
    return view
  }

  private val view by lazy {
    ComposeView(this).apply {
      consumeWindowInsets = false
      setContent {
        DroidSKKTheme {
          Keyboard()
        }
      }
    }
  }
}
