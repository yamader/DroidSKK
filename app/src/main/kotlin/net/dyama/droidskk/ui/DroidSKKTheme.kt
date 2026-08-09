package net.dyama.droidskk.ui

// generate with https://material-foundation.github.io/material-theme-builder/?primary=%23114514

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val lightScheme = lightColorScheme(
  primary = Color(0xFF3C6839),
  onPrimary = Color(0xFFFFFFFF),
  primaryContainer = Color(0xFFBDF0B3),
  onPrimaryContainer = Color(0xFF245023),
  secondary = Color(0xFF53634F),
  onSecondary = Color(0xFFFFFFFF),
  secondaryContainer = Color(0xFFD6E8CE),
  onSecondaryContainer = Color(0xFF3B4B38),
  tertiary = Color(0xFF38656A),
  onTertiary = Color(0xFFFFFFFF),
  tertiaryContainer = Color(0xFFBCEBF0),
  onTertiaryContainer = Color(0xFF1E4D52),
  error = Color(0xFFBA1A1A),
  onError = Color(0xFFFFFFFF),
  errorContainer = Color(0xFFFFDAD6),
  onErrorContainer = Color(0xFF93000A),
  background = Color(0xFFF7FBF1),
  onBackground = Color(0xFF191D17),
  surface = Color(0xFFF7FBF1),
  onSurface = Color(0xFF191D17),
  surfaceVariant = Color(0xFFDEE5D8),
  onSurfaceVariant = Color(0xFF424940),
  outline = Color(0xFF73796F),
  outlineVariant = Color(0xFFC2C8BD),
  scrim = Color(0xFF000000),
  inverseSurface = Color(0xFF2D322B),
  inverseOnSurface = Color(0xFFEFF2E9),
  inversePrimary = Color(0xFFA2D399),
  surfaceDim = Color(0xFFD8DBD2),
  surfaceBright = Color(0xFFF7FBF1),
  surfaceContainerLowest = Color(0xFFFFFFFF),
  surfaceContainerLow = Color(0xFFF1F5EB),
  surfaceContainer = Color(0xFFECEFE6),
  surfaceContainerHigh = Color(0xFFE6E9E0),
  surfaceContainerHighest = Color(0xFFE0E4DA),
)

private val darkScheme = darkColorScheme(
  primary = Color(0xFFA2D399),
  onPrimary = Color(0xFF0C390E),
  primaryContainer = Color(0xFF245023),
  onPrimaryContainer = Color(0xFFBDF0B3),
  secondary = Color(0xFFBACCB3),
  onSecondary = Color(0xFF253423),
  secondaryContainer = Color(0xFF3B4B38),
  onSecondaryContainer = Color(0xFFD6E8CE),
  tertiary = Color(0xFFA0CFD4),
  onTertiary = Color(0xFF00363B),
  tertiaryContainer = Color(0xFF1E4D52),
  onTertiaryContainer = Color(0xFFBCEBF0),
  error = Color(0xFFFFB4AB),
  onError = Color(0xFF690005),
  errorContainer = Color(0xFF93000A),
  onErrorContainer = Color(0xFFFFDAD6),
  background = Color(0xFF10140F),
  onBackground = Color(0xFFE0E4DA),
  surface = Color(0xFF10140F),
  onSurface = Color(0xFFE0E4DA),
  surfaceVariant = Color(0xFF424940),
  onSurfaceVariant = Color(0xFFC2C8BD),
  outline = Color(0xFF8C9388),
  outlineVariant = Color(0xFF424940),
  scrim = Color(0xFF000000),
  inverseSurface = Color(0xFFE0E4DA),
  inverseOnSurface = Color(0xFF2D322B),
  inversePrimary = Color(0xFF3C6839),
  surfaceDim = Color(0xFF10140F),
  surfaceBright = Color(0xFF363A34),
  surfaceContainerLowest = Color(0xFF0B0F0A),
  surfaceContainerLow = Color(0xFF191D17),
  surfaceContainer = Color(0xFF1D211B),
  surfaceContainerHigh = Color(0xFF272B25),
  surfaceContainerHighest = Color(0xFF323630),
)

@Composable
fun DroidSKKTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> darkScheme
    else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    content = content
  )
}
