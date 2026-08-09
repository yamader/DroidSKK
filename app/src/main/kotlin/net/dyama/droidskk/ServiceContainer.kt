package net.dyama.droidskk

import android.content.Context
import net.dyama.droidskk.skk.SKKEngine
import net.dyama.droidskk.skk.input.HiraganaInput
import net.dyama.droidskk.skk.mode.DirectMode
import net.dyama.droidskk.skk.service.HenkanService
import net.dyama.droidskk.skk.service.KanaService
import net.dyama.droidskk.skk.service.RomajiService

class ServiceContainer(serviceContext: Context) {
  private val appContainer = serviceContext.appContainer

  // skk
  val henkanService = HenkanService(appContainer.skkSourceRepository)
  val romajiService = RomajiService()
  val kanaService = KanaService()
  val skkEngine = SKKEngine(
    serviceContext,
    henkanService,
    romajiService,
    kanaService,
    // todo: change default by config
    defaultInput = { HiraganaInput(romajiService) },
    defaultMode = { DirectMode(HiraganaInput(romajiService)) },
  )
}
