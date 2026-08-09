package net.dyama.droidskk.skk.source

class SKKServClient(meta: SKKSourceMeta) : SKKSource {
  val source = meta.source

  override suspend fun henkan(midashi: String, okuri: String?): List<Candidate> {
    TODO("Not yet implemented")
  }

  override suspend fun yosoku(midahsi: String): List<Candidate> {
    TODO("Not yet implemented")
  }
}
