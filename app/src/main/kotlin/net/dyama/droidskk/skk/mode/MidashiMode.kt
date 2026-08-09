package net.dyama.droidskk.skk.mode

import kotlinx.coroutines.flow.toList
import net.dyama.droidskk.skk.input.SKKInput
import net.dyama.droidskk.skk.service.HenkanService

class MidashiMode(
  override var input: SKKInput,
  private val henkanService: HenkanService,
) : SKKMode {
  override val symbol = "▽"
  override var child: SKKMode? = null
  override var candidatesCount = 0U
  override var candidateCursor = 0U
  val midashi = StringBuilder()
  val okuri = StringBuilder()

  override fun state() =
    midashi.toString() + (if (okuri.isNotEmpty()) "*$okuri" else "") + input.state()

  override fun push(c: Char): String? {
    input.push(c)?.let { midashi.append(it) }
    return null
  }

  override fun abort(): String {
    val res = midashi.toString() + input.abort()
    midashi.clear()
    return res
  }

  override suspend fun candidates() = henkanService.yosoku(midashi.toString()).toList()

  private val dict = mapOf(
    "やわr" to listOf("柔", "軟", "和"),
    "やぶr" to listOf("破", "敗"),
    "やどs" to listOf("宿"),
  )
}
