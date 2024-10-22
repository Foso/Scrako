package me.jens.scratch.event

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import scratch.Broadcast

class WhenIRecieve(broadcast: Broadcast) : BlockSpec(
    opcode = OpCode.event_whenbroadcastreceived,
    fields = mapOf("BROADCAST_OPTION" to listOf(broadcast.name, broadcast.id.toString()))
), Event