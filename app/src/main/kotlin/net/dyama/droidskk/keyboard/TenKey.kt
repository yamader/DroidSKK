package net.dyama.droidskk.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.dyama.droidskk.DroidSKKService

@Composable
fun TenKey() {
  Column(Modifier.background(MaterialTheme.colorScheme.background)) {
    val width = DroidSKKService.displayMetrix()?.run { (widthPixels / density / 5).dp }
      ?: 32.dp

    KeysRow(5) {
      Key("←", Modifier.width(width))
      Key("あ", Modifier.width(width))
      Key("か", Modifier.width(width))
      Key("さ", Modifier.width(width))
      Key("→", Modifier.width(width))
    }
    KeysRow(5) {
      Key("←", Modifier.width(width))
      Key("た", Modifier.width(width))
      Key("な", Modifier.width(width))
      Key("は", Modifier.width(width))
    }
    KeysRow(5) {
      Key("X", Modifier.width(width))
      Key("ま", Modifier.width(width))
      Key("や", Modifier.width(width))
      Key("ら", Modifier.width(width))
    }
    KeysRow(5) {
      Key("A", Modifier.width(width))
      Key("a", Modifier.width(width))
      Key("わ", Modifier.width(width))
      Key("，", Modifier.width(width))
      Key("↓", Modifier.width(width))
    }
  }
}
