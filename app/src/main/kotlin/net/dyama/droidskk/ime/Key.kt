package net.dyama.droidskk.ime

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Key(c: String = "A") {
  FilledTonalButton(
    onClick = { /*TODO*/ },
    modifier = Modifier.width(32.dp),
    shape = MaterialTheme.shapes.medium,
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    contentPadding = PaddingValues(all = 0.dp),
  ) {
    Text(c)
  }
}
