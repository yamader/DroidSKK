package net.dyama.droidskk.ui.keyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import net.dyama.droidskk.R
import net.dyama.droidskk.ui.LocalSKKEngine

private val a = FlickKeyData('あ', 'い', 'う', 'え', 'お')
private val ka = FlickKeyData('か', 'き', 'く', 'け', 'こ')
private val sa = FlickKeyData('さ', 'し', 'す', 'せ', 'そ')
private val ta = FlickKeyData('た', 'ち', 'つ', 'て', 'と')
private val na = FlickKeyData('な', 'に', 'ぬ', 'ね', 'の')
private val ha = FlickKeyData('は', 'ひ', 'ふ', 'へ', 'ほ')
private val ma = FlickKeyData('ま', 'み', 'む', 'め', 'も')
private val ya = FlickKeyData('や', '(', 'ゆ', ')', 'よ')
private val ra = FlickKeyData('ら', 'り', 'る', 'れ', 'ろ')
private val wa = FlickKeyData('わ', 'を', 'ん', 'ー', '〜')

@Composable
fun KeysRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
  Row(
    modifier = modifier.padding(horizontal = 4.dp, vertical = 2.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp),
    content = content,
  )
}

@Composable
fun Key(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  content: @Composable RowScope.() -> Unit
) {
  FilledTonalButton(
    onClick = onClick,
    modifier = modifier.fillMaxSize(),
    shape = MaterialTheme.shapes.medium,
    contentPadding = PaddingValues(all = 0.dp),
    content = content,
  )
}

@Composable
fun TenKeys(modifier: Modifier = Modifier) {
  val skkEngine = LocalSKKEngine.current

  Column(modifier) {
    KeysRow(Modifier.weight(1f)) {
      Key({}, Modifier.weight(1f)) { Icon(painterResource(R.drawable.undo), "Undo") }
      FlickKey(a, Modifier.weight(1f))
      FlickKey(ka, Modifier.weight(1f))
      FlickKey(sa, Modifier.weight(1f))
      Key({}, Modifier.weight(1f)) { Icon(painterResource(R.drawable.backspace), "Backspace") }
    }
    KeysRow(Modifier.weight(1f)) {
      Key({}, Modifier.weight(1f)) { Icon(painterResource(R.drawable.arrow_left), "Arrow Left") }
      FlickKey(ta, Modifier.weight(1f))
      FlickKey(na, Modifier.weight(1f))
      FlickKey(ha, Modifier.weight(1f))
      Key({}, Modifier.weight(1f)) { Icon(painterResource(R.drawable.arrow_right), "Arrow Right") }
    }
    KeysRow(Modifier.weight(1f)) {
      Row(Modifier.weight(1f)) {
        Key({}, Modifier.weight(1f)) { Icon(painterResource(R.drawable.mood), "Mood") }
      }
      FlickKey(ma, Modifier.weight(1f))
      FlickKey(ya, Modifier.weight(1f))
      FlickKey(ra, Modifier.weight(1f))
      Key({}, Modifier.weight(1f)) { Icon(painterResource(R.drawable.space_bar), "Space Bar") }
    }
    KeysRow(Modifier.weight(1f)) {
      Key({}, Modifier.weight(1f)) {
        Icon(
          painterResource(R.drawable.language_japanese_kana),
          "Language Japanese Kana"
        )
      }
      Key({}, Modifier.weight(1f)) {}
      FlickKey(wa, Modifier.weight(1f))
      Key({}, Modifier.weight(1f)) {}
      Key({}, Modifier.weight(1f)) {
        Icon(
          painterResource(R.drawable.keyboard_return),
          "Keyboard Return"
        )
      }
    }
  }
}
