package net.dyama.droidskk.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.json.Json
import net.dyama.droidskk.skk.source.SKKServClient
import net.dyama.droidskk.skk.source.SKKSourceMeta
import net.dyama.droidskk.skk.source.SKKSourceType

class SKKSourceRepository(
  private val skkDictionaryRepository: SKKDictionaryRepository,
  prefs: DataStore<Preferences>,
) {
  val sourcesFlow = prefs.data.map { preferences ->
    Json.decodeFromString<List<SKKSourceMeta>>(preferences[SKK_SOURCES] ?: "[]").asFlow()
      .mapNotNull {
        when (it.type) {
          SKKSourceType.USER_DICT, SKKSourceType.SYSTEM_DICT ->
            skkDictionaryRepository.loadSource(it).getOrNull()

          SKKSourceType.SERVER -> SKKServClient(it)
        }
      }
  }
}
