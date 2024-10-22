package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode

class WhenIRecieve(broadcast: Broadcast) : BlockSpec(
    opcode = OpCode.event_whenbroadcastreceived,
    fields = mapOf("BROADCAST_OPTION" to listOf(broadcast.name, broadcast.id.toString()))
), Event

fun NodeBuilder.whenIRecieve(broadcast: Broadcast) = addChild(WhenIRecieve(broadcast))