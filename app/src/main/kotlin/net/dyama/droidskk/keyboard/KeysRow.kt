package net.dyama.droidskk.keyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun KeysRow(keys: Int, content: @Composable () -> Unit) {
  Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
    content()
  }
}
