package net.dyama.droidskk

import android.view.View
import androidx.compose.ui.platform.ComposeView
import net.dyama.droidskk.ime.IME
import net.dyama.droidskk.lib.LifecycleInputMethodService
import net.dyama.droidskk.lib.theme.DroidSKKTheme

class DroidSKKService: LifecycleInputMethodService() {
  private val view by lazy {
    ComposeView(this).apply {
      setContent {
        DroidSKKTheme {
          IME()
        }
      }
    }
  }

  override fun onCreateInputView(): View {
    installLifecycle()
    return view
  }
}
