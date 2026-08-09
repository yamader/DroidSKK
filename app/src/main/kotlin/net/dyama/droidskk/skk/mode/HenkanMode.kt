package net.dyama.droidskk.skk.mode

import kotlinx.coroutines.flow.toList
import net.dyama.droidskk.skk.input.SKKInput
import net.dyama.droidskk.skk.service.HenkanService

class HenkanMode(
  val midashi: String,
  val okuri: String?,
  override var input: SKKInput,
  private val henkanService: HenkanService,
) : SKKMode {
  override val symbol = "▼"
  override var child: SKKMode? = null
  override var candidatesCount = 0U
  override var candidateCursor = 0U

  override fun state(): String? {
    TODO("Not yet implemented")
  }

  override fun push(c: Char) = null

  override fun abort(): String? {
    // kakutei
    TODO("Not yet implemented")
  }

  override suspend fun candidates() =
    henkanService.henkan(midashi, okuri).toList()
}
