package net.dyama.droidskk.ime

import android.inputmethodservice.InputMethodService
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

open class LifecycleInputMethodService: InputMethodService(),
  LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {

  // 必須のメンバ
  override val lifecycle by lazy { LifecycleRegistry(this) }
  override val viewModelStore by lazy { ViewModelStore() }

  private val savedStateRegistryController by lazy { SavedStateRegistryController.create(this) }
  override val savedStateRegistry by lazy { savedStateRegistryController.savedStateRegistry }

  // ほげほげOwnerを設定
  fun installLifecycle() {
    val decorView = window.window!!.decorView
    decorView.setViewTreeLifecycleOwner(this)
    decorView.setViewTreeViewModelStoreOwner(this)
    decorView.setViewTreeSavedStateRegistryOwner(this)
  }

  // InputMethodServiceのイベントハンドラをoverrideする
  // 参考:
  // - androidx/activity/ComponentActivity.java
  //   - https://cs.android.com/search?q=file:androidx/activity/ComponentActivity.java+class:androidx.activity.ComponentActivity
  // - florisboard/ime/lifecycle/LifecycleInputMethodService.kt
  //   - https://github.com/florisboard/florisboard/blob/f11b3b8/app/src/main/kotlin/dev/patrickgold/florisboard/ime/lifecycle/LifecycleInputMethodService.kt

  @CallSuper
  override fun onCreate() {
    super.onCreate()
    savedStateRegistryController.performRestore(null)
    lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
  }

  @CallSuper
  override fun onWindowShown() {
    super.onWindowShown()
    lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
  }

  @CallSuper
  override fun onWindowHidden() {
    super.onWindowHidden()
    lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  }

  @CallSuper
  override fun onDestroy() {
    super.onDestroy()
    lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  }
}
