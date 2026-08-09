package net.dyama.droidskk.skk.service

class KanaService {
  fun hiragana(s: String): String {
    return s
  }

  fun katakana(s: String): String {
    return s.map { if (it.code in 0x3041..0x3093) it + 0x60 else it }.joinToString()
  }

  fun hankaku(s: String): String {
    return s
  }
}
