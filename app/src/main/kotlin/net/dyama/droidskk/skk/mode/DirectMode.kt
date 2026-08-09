package net.dyama.droidskk.skk.mode

import net.dyama.droidskk.skk.input.SKKInput

class DirectMode(override var input: SKKInput) : SKKMode {
  override val symbol = ""
  override var child: SKKMode? = null
  override var candidatesCount = 0U
  override var candidateCursor = 0U
  override fun state() = input.state()
  override fun push(c: Char) = input.push(c)
  override fun abort() = input.abort()
  override suspend fun candidates() = null
}
