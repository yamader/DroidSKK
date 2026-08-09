package net.dyama.droidskk.skk.input

import net.dyama.droidskk.skk.service.KanaService
import net.dyama.droidskk.skk.service.RomajiService

open class KatakanaInput(
  romajiService: RomajiService,
  private val kanaService: KanaService,
) : HiraganaInput(romajiService) {
  override fun push(c: Char) = super.push(c)?.let { kanaService.katakana(it) }
}
