package net.dyama.droidskk

import android.content.Context
import net.dyama.droidskk.data.SKKDictionaryRepository
import net.dyama.droidskk.data.SKKSourceRepository
import net.dyama.droidskk.data.prefs

class AppContainer(appContext: Context) {
  val skkDictionaryRepository = SKKDictionaryRepository(appContext.applicationContext)
  val skkSourceRepository = SKKSourceRepository(skkDictionaryRepository, appContext.prefs)
}
