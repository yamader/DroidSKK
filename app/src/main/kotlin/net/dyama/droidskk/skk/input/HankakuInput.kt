package net.dyama.droidskk.skk.input

import net.dyama.droidskk.skk.service.KanaService
import net.dyama.droidskk.skk.service.RomajiService

class HankakuInput(
  romajiService: RomajiService,
  private val kanaService: KanaService,
) : KatakanaInput(romajiService, kanaService) {
  override fun push(c: Char) = super.push(c)?.let { kanaService.hankaku(it) }
}
