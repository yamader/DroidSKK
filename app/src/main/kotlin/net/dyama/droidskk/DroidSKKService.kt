package net.dyama.droidskk

import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputConnection
import androidx.compose.ui.platform.ComposeView
import net.dyama.droidskk.keyboard.Keyboard
import net.dyama.droidskk.lib.LifecycleInputMethodService
import net.dyama.droidskk.lib.theme.DroidSKKTheme
import java.lang.ref.WeakReference

private var DroidSKKServiceRef = WeakReference<DroidSKKService>(null)

class DroidSKKService: LifecycleInputMethodService() {
  companion object {
    fun displayMetrix(): DisplayMetrics? = DroidSKKServiceRef.get()?.resources?.displayMetrics

    fun currentInputConnection(): InputConnection? =
      DroidSKKServiceRef.get()?.currentInputConnection
  }

  private val view by lazy {
    ComposeView(this).apply {
      setContent {
        DroidSKKTheme {
          Keyboard()
        }
      }
    }
  }

  override fun onCreate() {
    super.onCreate()
    DroidSKKServiceRef = WeakReference(this)
  }

  override fun onCreateInputView(): View {
    installLifecycle()
    return view
  }
}
