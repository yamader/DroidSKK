package net.dyama.droidskk.ui.common.theme

import android.app.Activity
import android.inputmethodservice.InputMethodService
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun AppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val context = LocalContext.current
  val view = LocalView.current

  val colorScheme = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
      if(darkTheme) dynamicDarkColorScheme(context)
      else dynamicLightColorScheme(context)

    darkTheme -> DarkColors
    else -> LightColors
  }

  if(!view.isInEditMode) SideEffect {
    // Activity
    (view.context as? Activity)?.window?.also { window ->
      // only with LightStatusBar and LightNavigationBar
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.statusBarColor = Color.Transparent.toArgb()
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        window.navigationBarColor = Color.Transparent.toArgb()

      WindowCompat.setDecorFitsSystemWindows(window, false)
      WindowCompat.getInsetsController(window, view).apply {
        isAppearanceLightStatusBars = !darkTheme
        isAppearanceLightNavigationBars = !darkTheme
      }
    }

    // Service
    (view.context as? InputMethodService)?.window?.also { dialog ->
      dialog.window?.navigationBarColor = Color.Transparent.toArgb()
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    content = content,
  )
}
