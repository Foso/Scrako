package me.jens.scratch.event

import me.jens.Event
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.OpCode
import scratch.Broadcast

class WhenKeyPress(private val key: String) : BlockSpecSpec(
    opcode = OpCode.event_whenkeypressed,
    fields = mapOf("KEY_OPTION" to listOf(key, null))
), Event


class ReceiveBroadcast(private val broadcast: Broadcast) : BlockSpecSpec(
    opcode = OpCode.event_whenbroadcastreceived,
    fields = mapOf("BROADCAST_OPTION" to listOf(broadcast.name, broadcast.id.toString()))
), Event

