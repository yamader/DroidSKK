package net.dyama.droidskk.skk.input

class ZenkakuInput : AsciiInput() {
  override fun push(c: Char) = "^" + super.push(c)
}
