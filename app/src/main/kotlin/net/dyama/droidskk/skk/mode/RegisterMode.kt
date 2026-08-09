package net.dyama.droidskk.skk.mode

import net.dyama.droidskk.skk.input.SKKInput

// okuri

class RegisterMode(
  val midashi: String,
  val okuri: String?,
  override var input: SKKInput, // unused
  override var child: SKKMode?,
) : SKKMode {
  override val symbol = "▼"
  override var candidatesCount = 0U
  override var candidateCursor = 0U

  override fun state(): String {
    TODO("Not yet implemented")
  }

  override fun push(c: Char) = null
  override fun abort() = null
  override suspend fun candidates() = null
}
