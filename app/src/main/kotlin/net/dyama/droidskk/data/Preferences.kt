package net.dyama.droidskk.data

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.prefs by preferencesDataStore("app")
val SKK_SOURCES = stringPreferencesKey("skk_sources")
