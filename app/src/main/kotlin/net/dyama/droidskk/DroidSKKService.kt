package net.dyama.droidskk

import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputConnection
import androidx.compose.ui.platform.ComposeView
import net.dyama.droidskk.ui.keyboard.Keyboard
import net.dyama.droidskk.ui.DroidSKKTheme
import net.dyama.droidskk.lib.LifecycleInputMethodService
import java.lang.ref.WeakReference

private var DroidSKKServiceRef = WeakReference<DroidSKKService>(null)

class DroidSKKService : LifecycleInputMethodService() {
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
