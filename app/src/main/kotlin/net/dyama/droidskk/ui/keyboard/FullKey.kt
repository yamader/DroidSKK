package net.dyama.droidskk.ui.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun FullKey() {
  Column(
    Modifier
      .background(MaterialTheme.colorScheme.background)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      KeysRow(10) {
        for(i in 0..9) {
          Key(i.toString())
        }
      }
      KeysRow(10) {
        for(c in "qwertyuiop") {
          Key(c.toString())
        }
      }
      KeysRow(9) {
        for(c in "asdfghjkl") {
          Key(c.toString())
        }
      }
      KeysRow(10) {
        Key("↑")
        for(c in "zxcvbnm") {
          Key(c.toString())
        }
        Key("←")
        Key("→")
      }
    }
  }
}

@Composable
fun KeysRow(keys: Int, content: @Composable () -> Unit) {
  Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
    content()
  }
}
