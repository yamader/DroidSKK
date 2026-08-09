package net.dyama.droidskk.data

import android.content.Context
import android.webkit.URLUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.dyama.droidskk.skk.source.Candidate
import net.dyama.droidskk.skk.source.SKKDictionary
import net.dyama.droidskk.skk.source.SKKSourceMeta
import java.io.File
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class SKKDictionaryRepository(
  private val context: Context,
) {
  fun dictFile(name: String, base: File = context.filesDir) = File(base, "dict/$name")

  suspend fun loadSource(meta: SKKSourceMeta) = load(meta.source)

  suspend fun load(name: String) = withContext(Dispatchers.IO) {
    // todo: cache
    runCatching {
      dictFile(name).inputStream().use { parseStream(it) }
    }
  }

  private fun parseStream(stream: InputStream): SKKDictionary {
    val entries = mutableMapOf<String, MutableList<Candidate>>()
    stream.reader().forEachLine { line ->
      if (line.startsWith(';')) return@forEachLine
      val (midashi, tail) = line.split(' ', limit = 2)
      val candidates = tail.trim('/').split('/').map { e ->
        e.split(';', limit = 2).let { Pair(it[0], it.getOrNull(1)) }
      }
      entries[midashi] = entries[midashi] ?: mutableListOf()
      entries[midashi]!! += candidates
    }
    return SKKDictionary(entries)
  }

  suspend fun update(meta: SKKSourceMeta) {
    if (!dictFile(meta.source).exists() && meta.origin != null) download(meta.origin, meta.source)
  }

  suspend fun download(url: URL, name: String) = withContext(Dispatchers.IO) {
    runCatching {
      val tmp = dictFile(name, context.cacheDir)
      url.openStream().use { inputStream ->
        tmp.outputStream().use { outputStream ->
          inputStream.copyTo(outputStream)
        }
      }
      tmp.copyTo(dictFile(name), overwrite = true)
      tmp.delete()
    }
  }

  suspend fun guessFileName(url: URL) = withContext(Dispatchers.IO) {
    runCatching {
      val c = url.openConnection() as HttpURLConnection
      try {
        URLUtil.guessFileName(
          url.toString(),
          c.getHeaderField("Content-Disposition"),
          c.contentType
        )
      } finally {
        c.disconnect()
      }
    }
  }
}
