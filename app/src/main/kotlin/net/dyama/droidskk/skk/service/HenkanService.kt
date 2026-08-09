package net.dyama.droidskk.skk.service

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import net.dyama.droidskk.data.SKKSourceRepository
import net.dyama.droidskk.lib.parallelFlatMapConcat

class HenkanService(private val skkSourceRepository: SKKSourceRepository) {
  suspend fun henkan(midashi: String, okuri: String?) =
    skkSourceRepository.sourcesFlow.first().parallelFlatMapConcat {
      it.henkan(midashi, okuri)?.asFlow() ?: emptyFlow()
    }

  suspend fun yosoku(midashi: String) =
    skkSourceRepository.sourcesFlow.first().parallelFlatMapConcat {
      it.yosoku(midashi)?.asFlow() ?: emptyFlow()
    }
}
