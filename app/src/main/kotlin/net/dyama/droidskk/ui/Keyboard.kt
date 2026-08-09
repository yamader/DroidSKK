package net.dyama.droidskk.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import net.dyama.droidskk.serviceContainer
import net.dyama.droidskk.skk.SKKEngine
import net.dyama.droidskk.ui.keyboard.ActionBar
import net.dyama.droidskk.ui.keyboard.TenKeys

val LocalSKKEngine = staticCompositionLocalOf<SKKEngine> { error("CompositionLocal not present") }

@Composable
fun Keyboard() {
  CompositionLocalProvider(
    LocalSKKEngine provides LocalContext.current.serviceContainer.skkEngine,
  ) {
    Column(
      Modifier
        .height(334.dp)
        .background(MaterialTheme.colorScheme.surfaceContainer)
        .systemBarsPadding()
    ) {
      ActionBar(Modifier.height(48.dp))
      TenKeys(Modifier.fillMaxSize())
    }
  }
}
