package net.dyama.droidskk.skk.input

import net.dyama.droidskk.skk.service.RomajiService

open class HiraganaInput(
  private val romaji: RomajiService,
) : SKKInput {
  private val buf = StringBuilder()

  override fun state() = buf.toString()

  override fun push(c: Char): String? {
    buf.append(c)
    val (next, kana) = romaji.findDropping(buf.toString())
    println("$next, $kana")
    buf.clear()
    buf.append(next)
    return kana
  }

  override fun abort(): String? {
    val res = buf.toString()
    buf.clear()
    return res
  }
}
