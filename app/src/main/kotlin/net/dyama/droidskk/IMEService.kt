package net.dyama.droidskk

import android.view.View
import androidx.compose.ui.platform.ComposeView
import net.dyama.droidskk.ime.LifecycleInputMethodService
import net.dyama.droidskk.ui.keyboard.Keyboard

class IMEService: LifecycleInputMethodService() {
  private val view by lazy {
    ComposeView(this).apply {
      setContent { Keyboard() }
    }
  }

  override fun onCreateInputView(): View {
    installLifecycle()
    return view
  }
}
