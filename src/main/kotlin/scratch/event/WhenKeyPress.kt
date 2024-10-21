package me.jens.scratch.event

import me.jens.Event
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.OpCode
import scratch.Broadcast
import scratch.Comment

class WhenKeyPress(key: String) : BlockSpecSpec(
    opcode = OpCode.event_whenkeypressed,
    fields = mapOf("KEY_OPTION" to listOf(key, null))
), Event


class ReceiveBroadcast(broadcast: Broadcast) : BlockSpecSpec(
    opcode = OpCode.event_whenbroadcastreceived,
    fields = mapOf("BROADCAST_OPTION" to listOf(broadcast.name, broadcast.id.toString()))
), Event

