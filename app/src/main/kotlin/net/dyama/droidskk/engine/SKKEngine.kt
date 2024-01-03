package net.dyama.droidskk.engine

import net.dyama.droidskk.DroidSKKService

object SKKEngine {
  private fun currentInputConnection() = DroidSKKService.currentInputConnection()

  var stack = mutableListOf<SKKState>()
  private var current: SKKState? = null

  fun start() {
    val state = SKKState()
    stack.add(state)
    current = state
  }

  fun push(c: String) {
    val ic = currentInputConnection()!!
    current?.also { curr ->
      curr.buf.append(c)
      curr.cursor++
      ic.setComposingText(curr.buf, curr.cursor)
    } ?: run {
      ic.commitText(c, 1)
    }
  }
}
