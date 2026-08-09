package net.dyama.droidskk.lib

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.flow.toList

fun <T, R> Flow<T>.parallelFlatMapConcat(transform: suspend (value: T) -> Flow<R>): Flow<R> =
  map(transform).parallelFlattenConcat()

fun <T> Flow<Flow<T>>.parallelFlattenConcat(): Flow<T> = channelFlow {
  map { it.produceIn(this) }.toList().forEach { flow -> flow.consumeEach { send(it) } }
}
