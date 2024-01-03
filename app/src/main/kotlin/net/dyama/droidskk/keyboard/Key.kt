package net.dyama.droidskk.keyboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.dyama.droidskk.engine.SKKEngine

@Composable
fun Key(c: String, modifier: Modifier = Modifier) {
  FilledTonalButton(
    onClick = {
      // wip
      SKKEngine.push(c)
    },
    modifier = modifier,
    shape = MaterialTheme.shapes.medium,
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    contentPadding = PaddingValues(all = 0.dp),
  ) {
    Text(c)
  }
}
