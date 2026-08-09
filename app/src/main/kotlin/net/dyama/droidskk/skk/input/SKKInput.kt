package net.dyama.droidskk.skk.input

interface SKKInput {
  fun state(): String?
  fun push(c: Char): String?
  fun abort(): String?
}
