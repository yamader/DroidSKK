@file:UseSerializers(URLSerializer::class)

package net.dyama.droidskk.skk.source

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import net.dyama.droidskk.lib.URLSerializer
import java.net.URL

typealias Candidate = Pair<String, String?>

@Serializable
data class SKKSourceMeta(
  val type: SKKSourceType,
  val source: String,
  val origin: URL?,
)

enum class SKKSourceType {
  SYSTEM_DICT,
  USER_DICT,
  SERVER,
}

interface SKKSource {
  suspend fun henkan(midashi: String, okuri: String?): List<Candidate>?
  suspend fun yosoku(midahsi: String): List<Candidate>?
}
