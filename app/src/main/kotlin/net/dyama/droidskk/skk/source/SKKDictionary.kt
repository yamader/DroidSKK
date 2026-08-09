package net.dyama.droidskk.skk.source

class SKKDictionary(private val entries: Map<String, List<Candidate>>) : SKKSource {
  override suspend fun henkan(midashi: String, okuri: String?) = when {
    okuri == null -> entries[midashi]
    entries.contains(midashi + okuri) -> entries[midashi + okuri]
    else -> entries[midashi]
  }

  override suspend fun yosoku(midahsi: String) =
    entries.filterKeys { it.startsWith(midahsi) }.values.flatten()
}
