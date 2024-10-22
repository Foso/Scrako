package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.Broadcast

class WhenIRecieve(broadcast: Broadcast) : BlockSpec(
    opcode = OpCode.event_whenbroadcastreceived,
    fields = mapOf("BROADCAST_OPTION" to listOf(broadcast.name, broadcast.id.toString()))
), Event