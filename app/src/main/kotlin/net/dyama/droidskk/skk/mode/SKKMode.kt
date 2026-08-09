package net.dyama.droidskk.skk.mode

import net.dyama.droidskk.skk.input.SKKInput

interface SKKMode {
  val symbol: String
  var input: SKKInput
  var child: SKKMode?

  fun state(): String?
  fun push(c: Char): String?
  fun abort(): String?

  suspend fun candidates(): List<Pair<String, String?>>?
  var candidatesCount: UInt
  var candidateCursor: UInt
}
