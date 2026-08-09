package net.dyama.droidskk.skk.input

open class AsciiInput : SKKInput {
  override fun state() = null
  override fun push(c: Char) = c.toString()
  override fun abort() = null
}
