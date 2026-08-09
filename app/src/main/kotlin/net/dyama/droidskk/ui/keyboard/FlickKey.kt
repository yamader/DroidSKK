package net.dyama.droidskk.ui.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.dyama.droidskk.ui.LocalSKKEngine
import kotlin.math.abs

data class FlickKeyData(
  val base: Char,
  val left: Char?,
  val up: Char?,
  val right: Char?,
  val down: Char?,
)

@Composable
fun FlickKey(
  data: FlickKeyData,
  modifier: Modifier = Modifier,
) {
  val skkEngine = LocalSKKEngine.current

  Box(
    modifier = modifier
      .fillMaxSize()
      .clip(MaterialTheme.shapes.small)
      .background(MaterialTheme.colorScheme.surfaceVariant)
      .pointerInput(data) {
        val threshold = 14.dp.toPx()
        awaitEachGesture {
          val down = awaitFirstDown()
          val id = down.id
          val start = down.position
          var end = start
          var released = false
          while (true) {
            val event = awaitPointerEvent()
            val change = event.changes.firstOrNull { it.id == id } ?: break
            if (!change.pressed) {
              end = change.position
              released = true
              break
            }
          }
          if (released) {
            val dx = end.x - start.x
            val dy = end.y - start.y
            val c = when {
              abs(dx) < threshold && abs(dy) < threshold -> data.base
              abs(dy) >= abs(dx) && dy < 0 -> data.up ?: data.base
              abs(dy) >= abs(dx) && dy > 0 -> data.down ?: data.base
              dx < 0 -> data.left ?: data.base
              else -> data.right ?: data.base
            }
            skkEngine.push(c)
          }
        }
      },
    contentAlignment = Alignment.Center,
  ) {
    Text(
      data.base.toString(),
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      style = MaterialTheme.typography.titleLarge,
    )
    data.up?.let {
      Text(
        it.toString(),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = 10.sp,
        modifier = Modifier
          .align(Alignment.TopCenter)
          .padding(top = 2.dp),
      )
    }
    data.left?.let {
      Text(
        it.toString(),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = 10.sp,
        modifier = Modifier
          .align(Alignment.CenterStart)
          .padding(start = 4.dp),
      )
    }
    data.right?.let {
      Text(
        it.toString(),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = 10.sp,
        modifier = Modifier
          .align(Alignment.CenterEnd)
          .padding(end = 4.dp),
      )
    }
    data.down?.let {
      Text(
        it.toString(),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = 10.sp,
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(bottom = 2.dp),
      )
    }
  }
}
