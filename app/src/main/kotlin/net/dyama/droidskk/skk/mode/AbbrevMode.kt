package net.dyama.droidskk.skk.mode

import net.dyama.droidskk.skk.input.SKKInput

class AbbrevMode(override var input: SKKInput) : SKKMode {
  override val symbol = "▽"
  override var child: SKKMode? = null
  override var candidatesCount = 0U
  override var candidateCursor = 0U
  private val buf = StringBuilder()

  override fun state() = buf.toString()

  override fun push(c: Char): String? {
    input.push(c)
    return null
  }

  override fun abort(): String {
    val res = buf.toString()
    buf.clear()
    return res
  }

  override suspend fun candidates(): List<Pair<String, String?>>? {
    TODO("Not yet implemented")
  }
}
