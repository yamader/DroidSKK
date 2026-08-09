package net.dyama.droidskk.skk

import android.content.Context
import net.dyama.droidskk.currentInputConnection
import net.dyama.droidskk.skk.input.AsciiInput
import net.dyama.droidskk.skk.input.HankakuInput
import net.dyama.droidskk.skk.input.HiraganaInput
import net.dyama.droidskk.skk.input.KatakanaInput
import net.dyama.droidskk.skk.input.SKKInput
import net.dyama.droidskk.skk.input.ZenkakuInput
import net.dyama.droidskk.skk.mode.AbbrevMode
import net.dyama.droidskk.skk.mode.DirectMode
import net.dyama.droidskk.skk.mode.HenkanMode
import net.dyama.droidskk.skk.mode.MidashiMode
import net.dyama.droidskk.skk.mode.RegisterMode
import net.dyama.droidskk.skk.mode.SKKMode
import net.dyama.droidskk.skk.service.HenkanService
import net.dyama.droidskk.skk.service.KanaService
import net.dyama.droidskk.skk.service.RomajiService

enum class InputCommand {
  HIRAGANA, KATAKANA, HANKAKU, ASCII, ZENKAKU,
}

enum class ModeCommand {
  MODE_DIRECT, MODE_MIDASHI, MODE_HENKAN, MODE_ABBREV,
  CONV_HIRAGANA, CONV_KATAKANA, CONV_HANKAKU,
  NEXT, PREV,
}

class SKKEngine(
  private val context: Context,
  private val henkanService: HenkanService,
  private val romajiService: RomajiService,
  private val kanaService: KanaService,
  private val defaultInput: () -> SKKInput,
  private val defaultMode: () -> SKKMode,
) {
  private var rootMode = defaultMode()
  private var mode: SKKMode
    get() = leaf()
    set(m) {
      leafParent()?.let {
        it.child = m
      } ?: {
        rootMode = m
      }
    }

  private fun leaf(m: SKKMode = rootMode): SKKMode =
    if (m.child == null) m
    else leaf(m.child!!)

  private fun leafParent(m: SKKMode = rootMode): SKKMode? =
    if (m.child == null) if (m == rootMode) null else m
    else leafParent(m.child!!)

  private fun pushMode(m: SKKMode) {
    mode.child = m
  }

  private fun popMode() {
    leafParent()?.let { it.child = null }
  }

  fun run(s: String) {
    val ic = context.currentInputConnection
    for (c in s) handle(c, false)?.let { ic.commitText(it, 1) }
    mode.state()?.let { ic.setComposingText(it, 1) }
  }

  fun push(c: Char) {
    val ic = context.currentInputConnection
    handle(c, false)?.let { ic.commitText(it, 1) }
    mode.state()?.let { ic.setComposingText(it, 1) }
  }

  fun handle(c: Char, control: Boolean): String? {
    modeCommand(c, control)?.let { cmd ->
      when (cmd) {
        ModeCommand.MODE_DIRECT -> {
          val res = mode.abort()
          mode = DirectMode(mode.input)
          // todo: submit newline
          return res
        }

        ModeCommand.MODE_MIDASHI -> mode = MidashiMode(mode.input, henkanService)

        ModeCommand.MODE_HENKAN -> {
          when (mode) {
            is MidashiMode -> {
              val mm = mode as MidashiMode
              pushMode(
                HenkanMode(
                  mm.midashi.toString(),
                  mm.okuri.toString(),
                  defaultInput(),
                  henkanService,
                )
              )
            }

            else -> error("invalid mode: $mode -> $cmd")
          }
        }

        ModeCommand.MODE_ABBREV -> {
          mode.abort()
          mode = AbbrevMode(AsciiInput())
        }

        ModeCommand.CONV_HIRAGANA -> return mode.abort()?.let { kanaService.hiragana(it) }
        ModeCommand.CONV_KATAKANA -> return mode.abort()?.let { kanaService.katakana(it) }
        ModeCommand.CONV_HANKAKU -> return mode.abort()?.let { kanaService.hankaku(it) }

        ModeCommand.NEXT -> {
          if (mode.candidateCursor >= mode.candidatesCount) {
            if (mode is HenkanMode) {
              val hm = mode as HenkanMode
              pushMode(
                RegisterMode(
                  hm.midashi,
                  hm.okuri,
                  defaultInput(),  // todo: unused
                  defaultMode(),
                )
              )
            }
            return null
          }
          mode.candidateCursor.inc()
        }

        ModeCommand.PREV -> {
          if (mode.candidateCursor == 0U) {
            if (mode is HenkanMode) popMode()
            return null
          }
          mode.candidateCursor.dec()
        }
      }
      return null
    }

    inputCommand(c, control)?.let { cmd ->
      mode.input = when (cmd) {
        InputCommand.HIRAGANA -> HiraganaInput(romajiService)
        InputCommand.KATAKANA -> KatakanaInput(romajiService, kanaService)
        InputCommand.HANKAKU -> HankakuInput(romajiService, kanaService)
        InputCommand.ASCII -> AsciiInput()
        InputCommand.ZENKAKU -> ZenkakuInput()
      }
      return null
    }

    return mode.push(c)
  }

  private fun defaultInputCommand(c: Char, control: Boolean) = when (Pair(c, control)) {
    Pair('j', true) -> InputCommand.HIRAGANA
    Pair('q', false) -> InputCommand.KATAKANA
    Pair('q', true) -> InputCommand.HANKAKU
    Pair('l', false) -> InputCommand.ASCII
    Pair('L', false) -> InputCommand.ZENKAKU
    else -> null
  }

  private fun inputCommand(c: Char, control: Boolean) = when (mode.input) {
    is HankakuInput -> when (Pair(c, control)) {
      Pair('j', true) -> null
      Pair('q', false) -> InputCommand.HIRAGANA
      Pair('q', true) -> InputCommand.HIRAGANA
      else -> defaultInputCommand(c, control)
    }

    is KatakanaInput -> when (Pair(c, control)) {
      Pair('j', true) -> null
      Pair('q', false) -> InputCommand.HIRAGANA
      else -> defaultInputCommand(c, control)
    }

    is HiraganaInput -> when (Pair(c, control)) {
      Pair('j', true) -> null
      else -> defaultInputCommand(c, control)
    }

    is ZenkakuInput -> when (Pair(c, control)) {
      Pair('j', true) -> InputCommand.HIRAGANA
      else -> null
    }

    is AsciiInput -> when (Pair(c, control)) {
      Pair('j', true) -> InputCommand.HIRAGANA
      else -> null
    }

    else -> null
  }

  private fun modeCommand(c: Char, control: Boolean) = when (mode) {
    is DirectMode -> when {
      mode.input is HiraganaInput && c.isUpperCase() -> ModeCommand.MODE_MIDASHI
      mode.input is HiraganaInput && c == '/' -> ModeCommand.MODE_ABBREV
      else -> null
    }

    // is MidashiMode if mode.isOkuriMode -> ModeCommand.MODE_HENKAN

    is MidashiMode -> when (c) {
      'a' if c.isUpperCase() -> null
      'q' if control -> if (mode.input is KatakanaInput) ModeCommand.CONV_HIRAGANA else ModeCommand.CONV_HANKAKU
      'q' -> if (mode.input is KatakanaInput) ModeCommand.CONV_HIRAGANA else ModeCommand.CONV_KATAKANA
      'j' if control -> ModeCommand.MODE_DIRECT
      '\n' -> ModeCommand.MODE_DIRECT
      '\t' -> ModeCommand.NEXT
      ' ' -> ModeCommand.MODE_HENKAN
      else -> null
    }

    is HenkanMode -> when (c) {
      ' ' -> ModeCommand.NEXT
      'x' -> ModeCommand.PREV
      else -> ModeCommand.MODE_DIRECT
    }

    is RegisterMode -> null // C-j to henkan
    is AbbrevMode -> null
    else -> null
  }
}
