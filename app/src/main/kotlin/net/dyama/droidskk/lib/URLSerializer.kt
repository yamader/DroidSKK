package net.dyama.droidskk.lib

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.URL

object URLSerializer : KSerializer<URL> {
  override val descriptor get() = buildClassSerialDescriptor("URL")
  override fun serialize(encoder: Encoder, value: URL) = encoder.encodeString(value.toString())
  override fun deserialize(decoder: Decoder) = URL(decoder.decodeString())
}
